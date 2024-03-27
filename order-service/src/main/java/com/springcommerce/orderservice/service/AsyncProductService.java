package com.springcommerce.orderservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;

import com.springcommerce.orderservice.dto.CreateOrderRequest;
import com.springcommerce.orderservice.dto.OrderedProductDTO;
import com.springcommerce.orderservice.dto.OrderedProductRequest;
import com.springcommerce.orderservice.dto.Response;
import com.springcommerce.orderservice.entity.Product;

@SuppressWarnings("unused")
public interface AsyncProductService {
	void upsert(Product product);
	void update(Product product);
	void deleteProduct(Product product);
}
