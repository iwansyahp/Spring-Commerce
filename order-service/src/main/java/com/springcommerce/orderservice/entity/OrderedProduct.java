package com.springcommerce.orderservice.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderedProduct {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="order_id")
	private Order order;
	
	private UUID productUuid;
	
	private Integer count = 1;
	
	private float totalProductPay;

	public OrderedProduct() {
		super();
	}

	public OrderedProduct(Order order, UUID productUuid, Integer count, float totalProductPay) {
		super();
		this.order = order;
		this.productUuid = productUuid;
		this.count = count;
		this.totalProductPay = totalProductPay;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public UUID getId() {
		return id;
	}

	public UUID getProductUuid() {
		return productUuid;
	}

	public float getTotalProductPay() {
		return totalProductPay;
	}

	public void setTotalProductPay(float totalProductPay) {
		this.totalProductPay = totalProductPay;
	}
	
}
