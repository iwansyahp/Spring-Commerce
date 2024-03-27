package com.springcommerce.productservice.service;

import com.springcommerce.events.payload.KafkaPayload;
import com.springcommerce.events.payload.KafkaProduct;

public interface KafkaProducerService {
	void sendProductMessage(String topicName, KafkaPayload<KafkaProduct> event);
}
