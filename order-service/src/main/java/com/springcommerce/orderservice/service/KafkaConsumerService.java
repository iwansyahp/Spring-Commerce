package com.springcommerce.orderservice.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import com.springcommerce.events.payload.KafkaProduct;

public interface KafkaConsumerService {
	void consumeProductTopic(ConsumerRecord<String, KafkaProduct> event);
}
