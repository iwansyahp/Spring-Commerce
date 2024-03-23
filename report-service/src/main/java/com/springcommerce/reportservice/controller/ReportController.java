package com.springcommerce.reportservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springcommerce.reportservice.dto.OrderCount;
import com.springcommerce.reportservice.mongodb.document.Order;
import com.springcommerce.reportservice.service.MongoService;

@ResponseStatus(HttpStatus.NOT_FOUND)  
class NotFoundException extends RuntimeException {  
	private static final long serialVersionUID = 4090200991609253864L;

	public NotFoundException(String message){
		super(message);
	} 
}  

@RestController
public class ReportController {
	
	@Autowired
	MongoService mongoService;
	
	@GetMapping("/reports/orders")
	public List<Order> getOrders() {
		try {
			return mongoService.getOrders();
		} catch (Exception e) {
			throw new NotFoundException("there is no orders");
		}
	}
	
	@GetMapping("/reports/orders/count")
	public ResponseEntity<OrderCount> getOrdersCount() {
		try {
			long orderCount = mongoService.getTotalOrders();
			return ResponseEntity.ok(new OrderCount(orderCount));
		} catch (Exception e) {
			throw new NotFoundException("there is no orders");
		}
	}
	
	@GetMapping("/reports/orders/{id}")
	public ResponseEntity<Order> getOrders(@PathVariable("id") String id) {
		try {
			Order order = mongoService.getOrderById(id);
			return ResponseEntity.ok(order);
		} catch (Exception e) {
			throw new NotFoundException("there is no orders");
		}
	}
	
}

