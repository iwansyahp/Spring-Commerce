package com.springcommerce.userservice.service;

import com.springcommerce.events.payload.KafkaPayload;
import com.springcommerce.events.payload.KafkaUser;

public interface KafkaUserProducerService {
    void sendUserEvent(String topicName, KafkaPayload<KafkaUser> event);
}
