package com.springcommerce.orderservice.kafka;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.springcommerce.orderservice.entity.Product;

public class KafkaProductMessage {
	private String key;
	private Product product;
	
	@JsonCreator
	public KafkaProductMessage(@JsonProperty("key") String key, @JsonProperty("data") Product product) {
		super();
		this.key = key;
		this.product = product;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	@Override
	public String toString() {
		return "KafkaEventMessage [key=" + key + ", data=" + product + "]";
	}
}