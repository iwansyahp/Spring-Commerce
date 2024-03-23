package com.springcommerce.productservice.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springcommerce.productservice.dto.Response;
import com.springcommerce.productservice.dto.CreateUpdateProductRequest;
import com.springcommerce.productservice.entity.Product;
import com.springcommerce.productservice.kafka.KafkaConstants;
import com.springcommerce.productservice.kafka.KafkaProductMessage;
import com.springcommerce.productservice.repository.ProductRepository;
import com.springcommerce.productservice.service.KafkaProducerService;
import com.springcommerce.productservice.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	KafkaProducerService kafkaProducerService;
	
	public Page<Product> findAllByActiveOrderByNameAsc(boolean active, Pageable pageable) {
		return productRepository.findAllByActiveOrderByNameAsc(active, pageable);
	}
	
	public Page<Product> findAll(Pageable pageable) {
		return productRepository.findAll(pageable);
	}
	
	public Optional<Product> findProductByUuid(UUID uuid) {
		return productRepository.findByUuid(uuid);
	}
	
	public void saveAll(List<Product> products) {
		productRepository.saveAll(products);
		
		/** update replication via kafka */
		products.stream().forEach(product -> 
			kafkaProducerService.sendProductMessage(KafkaConstants.PRODUCT_TOPIC, new KafkaProductMessage(KafkaConstants.PRODUCT_CREATE_KEY, product))
		);
	}

	@Override
	public ResponseEntity<Response<Product>> create(CreateUpdateProductRequest request) {
		Product product = new Product(request.getName(), request.getPrice(), request.getAvailability());
		productRepository.save(product);
		
		kafkaProducerService.sendProductMessage(
				KafkaConstants.PRODUCT_TOPIC, new KafkaProductMessage(KafkaConstants.PRODUCT_CREATE_KEY, product)
		);
		
		Response<Product> response = new Response<>(product);
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<Response<Product>> update(UUID uuid, CreateUpdateProductRequest request) {
		Optional<Product> productOpt = this.findProductByUuid(uuid);
		
		if (!productOpt.isPresent()) {
			return new ResponseEntity(
					new Response(String.format("product with uuid %s can not be found", uuid)
			), HttpStatus.BAD_REQUEST);
		}
		Product product = productOpt.get();
		product.setActive(request.isActive());
		product.setName(request.getName());
		product.setAvailability(request.getAvailability());
		product.setPrice(request.getPrice());
		
		productRepository.save(product);
		
		kafkaProducerService.sendProductMessage(KafkaConstants.PRODUCT_TOPIC, new KafkaProductMessage(KafkaConstants.PRODUCT_UPDATE_KEY, product));
		
		Response<Product> response = new Response<>(product);
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<Response<Product>>  deleteProduct(UUID uuid) {
		Optional<Product> product = productRepository.findByUuid(uuid);
		
		Product cloneProduct = product.get();
		if (product.isPresent()) {
			productRepository.deleteByUuid(cloneProduct.getUuid());
			kafkaProducerService.sendProductMessage(
					KafkaConstants.PRODUCT_TOPIC, new KafkaProductMessage(KafkaConstants.PRODUCT_DELETE_KEY, cloneProduct)
			);
		}
		
		return new ResponseEntity(
				new Response(String.format("Product with uuid %s is removed", uuid)
		), HttpStatus.OK);
	}

}
