package com.springcommerce.orderservice.kafka;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.springcommerce.orderservice.dto.OrderDTO;

public class KafkaOrderMessage {
	private String key;
	private OrderDTO order;
	
	@JsonCreator
	public KafkaOrderMessage(@JsonProperty("key") String key, @JsonProperty("data") OrderDTO order) {
		super();
		this.key = key;
		this.order = order;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public OrderDTO getOrder() {
		return order;
	}
	public void setOrder(OrderDTO order) {
		this.order = order;
	}
	
	@Override
	public String toString() {
		return "KafkaEventMessage [key=" + key + ", data=" + order + "]";
	}
}