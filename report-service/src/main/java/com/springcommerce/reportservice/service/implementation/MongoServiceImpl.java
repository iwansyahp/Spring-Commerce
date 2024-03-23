package com.springcommerce.reportservice.service.implementation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springcommerce.reportservice.mongodb.document.Order;
import com.springcommerce.reportservice.repository.OrderRepository;
import com.springcommerce.reportservice.service.MongoService;

@Service
public class MongoServiceImpl implements MongoService {

	private final Logger logger = LoggerFactory.getLogger(MongoServiceImpl.class);
	
	@Autowired
	OrderRepository orderRepository;
	
	@Override
	public Order getOrderById(String id) {
		return orderRepository.findById(id).orElse(null);
	}

	@Override
	public List<Order> getOrders() {
		List<Order> orders = orderRepository.findAll();
		Order order = orders.get(0);
		logger.warn(order.getOrderName());
		return orders;
	}

	@Override
	public long getTotalOrders() {
		return orderRepository.count();
	}

	@Override
	public void storeOrder(Order order) {
		orderRepository.save(order);
	}

}
