package com.springcommerce.orderservice.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import com.springcommerce.events.payload.KafkaProduct;
import com.springcommerce.events.payload.KafkaUser;

public interface KafkaConsumerService {
	void consumeProductTopic(ConsumerRecord<String, KafkaProduct> event);

	void consumeUserTopic(ConsumerRecord<String, KafkaUser> event);
}
