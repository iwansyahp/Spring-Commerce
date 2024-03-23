package com.springcommerce.orderservice.service;

import com.springcommerce.orderservice.kafka.KafkaProductMessage;

public interface KafkaConsumerService {
	void consumeProductTopic(KafkaProductMessage event);
}
