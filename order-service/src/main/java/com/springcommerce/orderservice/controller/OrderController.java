package com.springcommerce.orderservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springcommerce.orderservice.dto.CreateOrderRequest;
import com.springcommerce.orderservice.dto.OrderDTO;
import com.springcommerce.orderservice.dto.OrderRequest;
import com.springcommerce.orderservice.dto.OrderedProductDTO;
import com.springcommerce.orderservice.dto.Response;
import com.springcommerce.orderservice.service.OrderService;

@RestController
public class OrderController {

	protected Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	OrderService orderService;

	@GetMapping("/orders/{id}")
	public ResponseEntity<Response<OrderDTO>> getOrderById(@PathVariable String id) {
		ResponseEntity<Response<OrderDTO>> response = orderService.findOrderById(id);
		return response;
	}
	
	@PostMapping("/orders")
	public ResponseEntity<Response<OrderedProductDTO>> createOrder(@RequestBody CreateOrderRequest request) {
		logger.warn(request.toString());
		ResponseEntity<Response<OrderedProductDTO>> response = orderService.createOrder(request);
		return response;
	}
	
	@PutMapping("/orders/cancel")
	public ResponseEntity<Response<OrderedProductDTO>> cancelOrder(@RequestBody OrderRequest request) {
		orderService.cancelOrder(request);
		return new ResponseEntity(HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/orders/pay")
	public ResponseEntity<Response<OrderedProductDTO>> payOrder(@RequestBody OrderRequest request) {
		orderService.payOrder(request);
		return new ResponseEntity(HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/orders/checkout")
	public ResponseEntity<Response<OrderedProductDTO>> checkOutOrder(@RequestBody OrderRequest request) {
		orderService.checkOutOrder(request);
		return new ResponseEntity(HttpStatus.ACCEPTED);
	}
}
