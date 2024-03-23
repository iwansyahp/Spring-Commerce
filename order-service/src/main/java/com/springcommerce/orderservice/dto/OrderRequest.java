package com.springcommerce.orderservice.dto;

import java.util.UUID;

public class OrderRequest {
	private UUID uuid;

	public OrderRequest() {
		super();
	}

	public OrderRequest(UUID uuid) {
		super();
		this.uuid = uuid;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	
}
