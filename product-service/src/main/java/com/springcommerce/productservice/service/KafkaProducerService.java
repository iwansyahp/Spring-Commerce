package com.springcommerce.productservice.service;

import org.springframework.stereotype.Service;

import com.springcommerce.productservice.kafka.KafkaProductMessage;

@Service
public interface KafkaProducerService {
	void sendProductMessage(String topicName, KafkaProductMessage event);
}
