package com.springcommerce.reportservice.dto;

public class OrderCount {
	private long orderCount;

	public OrderCount() {
		super();
	}

	public OrderCount(long orderCount) {
		super();
		this.orderCount = orderCount;
	}

	public long getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(long orderCount) {
		this.orderCount = orderCount;
	}
	
}
