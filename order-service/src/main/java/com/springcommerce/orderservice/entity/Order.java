package com.springcommerce.orderservice.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.springcommerce.orderservice.configuration.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders", schema = "public")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	private String orderName;

	private Float totalPay;

	@Column(nullable = false)
	private OrderStatus status = OrderStatus.WAITING_FOR_PAYMENT;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<OrderedProduct> orderedProducts = new ArrayList<>();

	@CreationTimestamp
	private Date createdAt;

	@UpdateTimestamp
	private Date updatedAt;

	public Order(String orderName, OrderStatus status) {
		super();
		this.orderName = orderName;
		this.status = status;
	}


	public Order() {
		super();
	}


	public Order(UUID id, String orderName, Float totalPay, OrderStatus status, List<OrderedProduct> orderedProducts,
			Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.orderName = orderName;
		this.totalPay = totalPay;
		this.status = status;
		this.orderedProducts = orderedProducts;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}


	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public List<OrderedProduct> getOrderedProducts() {
		return orderedProducts;
	}

	public void setOrderedProducts(List<OrderedProduct> orderedProducts) {
		this.orderedProducts = orderedProducts;
	}

	public UUID getId() {
		return id;
	}

	public String getOrderName() {
		return orderName;
	}

	public Float getTotalPay() {
		return totalPay;
	}

	public void setTotalPay(Float totalPay) {
		this.totalPay = totalPay;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}
}
