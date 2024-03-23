package com.springcommerce.reportservice.service;

import java.util.List;

import com.springcommerce.reportservice.mongodb.document.Order;

public interface MongoService {
	Order getOrderById(String id);
	List<Order> getOrders();
	long getTotalOrders();
	void storeOrder(Order order);
}
