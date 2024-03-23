package com.springcommerce.orderservice.dto;

import java.util.UUID;

public class OrderedProductRequest {
	private UUID productUuid;
	private int count;
	
	public OrderedProductRequest(UUID productUuid, int count) {
		super();
		this.productUuid = productUuid;
		this.count = count;
	}
	
	public UUID getProductUuid() {
		return productUuid;
	}

	public void setProductUuid(UUID productUuid) {
		this.productUuid = productUuid;
	}

	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
