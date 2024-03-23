package com.springcommerce.orderservice.dto;

import java.util.List;

public class CreateOrderRequest {
	private List<OrderedProductRequest> orderedProducts;

	public CreateOrderRequest() {
		super();
	}

	public CreateOrderRequest(List<OrderedProductRequest> orderedProducts) {
		super();
		this.orderedProducts = orderedProducts;
	}

	public List<OrderedProductRequest> getOrderedProducts() {
		return orderedProducts;
	}

	public void setOrderedProducts(List<OrderedProductRequest> orderedProducts) {
		this.orderedProducts = orderedProducts;
	}
}
