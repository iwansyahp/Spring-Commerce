package com.springcommerce.orderservice.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springcommerce.events.OrderEvents;
import com.springcommerce.events.payload.KafkaPayload;
import com.springcommerce.orderservice.configuration.OrderStatus;
import com.springcommerce.orderservice.dto.CreateOrderRequest;
import com.springcommerce.orderservice.dto.OrderDTO;
import com.springcommerce.orderservice.dto.OrderRequest;
import com.springcommerce.orderservice.dto.OrderedProductDTO;
import com.springcommerce.orderservice.dto.OrderedProductRequest;
import com.springcommerce.orderservice.dto.Response;
import com.springcommerce.orderservice.entity.Order;
import com.springcommerce.orderservice.entity.OrderedProduct;
import com.springcommerce.orderservice.entity.Product;
import com.springcommerce.orderservice.repository.OrderRepository;
import com.springcommerce.orderservice.repository.OrderedProductRepository;
import com.springcommerce.orderservice.repository.ProductRepository;
import com.springcommerce.orderservice.service.KafkaProducerService;
import com.springcommerce.orderservice.service.OrderService;
import com.springcommerce.orderservice.util.OrderUtils;
import com.springcommerce.orderservice.util.RandomGenerator;

@Service
public class OrderServiceImpl implements OrderService {

	private Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	OrderedProductRepository orderedProductRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	KafkaProducerService kafkaProducerService;

	@Override
	public ResponseEntity<Response<OrderDTO>> findOrderById(String orderId) {

		Optional<Order> orderFromDB = orderRepository.findById(UUID.fromString(orderId));

		if (!orderFromDB.isPresent()) {
			logger.error("can not find an order with order id: {}", orderId);
			return new ResponseEntity<Response<OrderDTO>>(HttpStatus.BAD_REQUEST);
		}

		Order order = orderFromDB.get();

		Response<OrderDTO> response = new Response<OrderDTO>(new OrderDTO(order.getId(), order.getOrderName(), order.getTotalPay(), order.getStatus().toString(), order.getCreatedAt(), order.getUpdatedAt()));

		return ResponseEntity.ok(response);
	}

	@Override
	@Transactional
	public ResponseEntity<Response<OrderedProductDTO>> createOrder(CreateOrderRequest request) {
		// check if there is any product with order count 1
		List<OrderedProductRequest> orderedProductsReq = request.getOrderedProducts();
		boolean invalidOrderedProduct = orderedProductsReq
				.stream()
				.anyMatch(product -> product.getCount() < 1);

		if (invalidOrderedProduct) {
			return new ResponseEntity(new Response("invalid count for ordered product"), HttpStatus.BAD_REQUEST);
		}

		// extract product ids
		List<UUID> productUuids = orderedProductsReq
				.stream()
				.map(OrderedProductRequest::getProductUuid)
				.collect(Collectors.toList());
		logger.warn(request.toString());

		// get products
		List<Product> products = productRepository.findAllByUuidIn(productUuids);
		logger.warn(productUuids.toString());

		if (products.size() != productUuids.size()) {
			return new ResponseEntity(new Response("some products can not be found"), HttpStatus.BAD_REQUEST);
		}

		// create order
		Order order = new Order(RandomGenerator.generateRandomName(), OrderStatus.ORDER_PLACED);

		float totalPay = 0;
		List<OrderedProduct> orderedProducts = new ArrayList<>();
		for (OrderedProductRequest orderedProduct: orderedProductsReq) {
			Optional<Product> product = products.stream().filter(p -> p.getUuid().equals(orderedProduct.getProductUuid())).findFirst();
			logger.warn(product.toString());
			if (product.isPresent()) {
				float productPay = orderedProduct.getCount() * product.get().getPrice();
				OrderedProduct orderProduct = new OrderedProduct(order, orderedProduct.getProductUuid(), orderedProduct.getCount(), productPay);
				totalPay += productPay;
				orderedProducts.add(orderProduct);
			}
		}
		logger.warn(orderedProducts.toString());
		order.setOrderedProducts(orderedProducts);
		order.setTotalPay(totalPay);

		if (order.getOrderedProducts().isEmpty()) {
			return new ResponseEntity(new Response("product(s) can not be ordered"), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		orderRepository.save(order);
		orderedProductRepository.saveAll(orderedProducts);

		Response<OrderedProductDTO> response = new Response<OrderedProductDTO>(new OrderedProductDTO(order, orderedProducts));
		return ResponseEntity.ok(response);
	}

	@Override
	@Transactional
	public void cancelOrder(OrderRequest request) {
		String uuid = request.getUuid().toString();
		Optional<Order> order = orderRepository.findById(UUID.fromString(uuid));

		if (order.isPresent()) {
			Order orderFromDB = order.get();
			orderFromDB.setStatus(OrderStatus.CANCELLED);
			orderRepository.save(orderFromDB);

			/** send order to reports **/
			kafkaProducerService.sendOrderMessage(OrderEvents.ORDER_TOPIC, new KafkaPayload<>(OrderEvents.ORDER_CANCEL, OrderUtils.entityProductToKafkaProduct(orderFromDB)));
		}
	}

	@Override
	@Transactional
	public void payOrder(OrderRequest request) {
		String uuid = request.getUuid().toString();
		Optional<Order> order = orderRepository.findById(UUID.fromString(uuid));

		if (order.isPresent()) {
			var orderFromDB = order.get();
			orderFromDB.setStatus(OrderStatus.PAID);
			// List<OrderedProduct> orderedProducts = orderFromDB.getOrderedProducts();

			/** send order to reports **/
			kafkaProducerService.sendOrderMessage(OrderEvents.ORDER_TOPIC, new KafkaPayload<>(OrderEvents.ORDER_PAY, OrderUtils.entityProductToKafkaProduct(orderFromDB)));
		}
	}

	@Override
	public void checkOutOrder(OrderRequest request) {
		String uuid = request.getUuid().toString();
		Optional<Order> order = orderRepository.findById(UUID.fromString(uuid));

		if (order.isPresent()) {
			var orderFromDB = order.get();
			orderFromDB.setStatus(OrderStatus.WAITING_FOR_PAYMENT);
			orderRepository.save(orderFromDB);

			/** send order to reports **/
			kafkaProducerService.sendOrderMessage(OrderEvents.ORDER_TOPIC, new KafkaPayload<>(OrderEvents.ORDER_CHECK_OUT, OrderUtils.entityProductToKafkaProduct(orderFromDB)));
		}
	}
}
