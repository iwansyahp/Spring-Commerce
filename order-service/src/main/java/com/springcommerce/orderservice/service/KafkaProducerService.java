package com.springcommerce.orderservice.service;

import org.springframework.stereotype.Service;

import com.springcommerce.events.payload.KafkaOrder;
import com.springcommerce.events.payload.KafkaPayload;


@Service
public interface KafkaProducerService {
	void sendOrderMessage(String topicName, KafkaPayload<KafkaOrder> event);
}
