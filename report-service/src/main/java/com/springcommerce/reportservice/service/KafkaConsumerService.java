package com.springcommerce.reportservice.service;

import com.springcommerce.reportservice.kafka.KafkaOrderMessage;

public interface KafkaConsumerService {
	void consumeOrderTopic(KafkaOrderMessage event);
}
