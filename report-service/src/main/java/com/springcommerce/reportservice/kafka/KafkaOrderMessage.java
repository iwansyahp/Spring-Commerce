package com.springcommerce.reportservice.kafka;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class KafkaOrderMessage {
	private String key;
	private Order order;
	
	@JsonCreator
	public KafkaOrderMessage(@JsonProperty("key") String key, @JsonProperty("data") Order order) {
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
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	@Override
	public String toString() {
		return "KafkaEventMessage [key=" + key + ", data=" + order + "]";
	}
}