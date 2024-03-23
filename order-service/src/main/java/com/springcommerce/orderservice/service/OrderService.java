package com.springcommerce.orderservice.service;

import org.springframework.http.ResponseEntity;

import com.springcommerce.orderservice.dto.CreateOrderRequest;
import com.springcommerce.orderservice.dto.OrderDTO;
import com.springcommerce.orderservice.dto.OrderedProductDTO;
import com.springcommerce.orderservice.dto.OrderRequest;
import com.springcommerce.orderservice.dto.Response;

public interface OrderService {
	ResponseEntity<Response<OrderDTO>> findOrderById(String id);
	ResponseEntity<Response<OrderedProductDTO>> createOrder(CreateOrderRequest request);
	void cancelOrder(OrderRequest request);
	void payOrder(OrderRequest request);
	void checkOutOrder(OrderRequest request);
}
