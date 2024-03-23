package com.springcommerce.orderservice.service;

import org.springframework.stereotype.Service;

import com.springcommerce.orderservice.kafka.KafkaOrderMessage;


@Service
public interface KafkaProducerService {
	void sendOrderMessage(String topicName, KafkaOrderMessage event);
}
