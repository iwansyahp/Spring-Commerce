package com.springcommerce.orderservice.dto;

import java.util.List;

import com.springcommerce.orderservice.entity.Order;
import com.springcommerce.orderservice.entity.OrderedProduct;

public class OrderedProductDTO {
	private Order order;
	private List<OrderedProduct> orderedProducts;
	
	public OrderedProductDTO() {
		super();
	}
	public OrderedProductDTO(Order order, List<OrderedProduct> orderedProducts) {
		super();
		this.order = order;
		this.orderedProducts = orderedProducts;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public List<OrderedProduct> getOrderedProducts() {
		return orderedProducts;
	}
	public void setOrderedProducts(List<OrderedProduct> orderedProducts) {
		this.orderedProducts = orderedProducts;
	}
	
}
