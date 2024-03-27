package com.springcommerce.userservice.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.springcommerce.events.payload.KafkaPayload;
import com.springcommerce.events.payload.KafkaUser;
import com.springcommerce.userservice.service.KafkaUserProducerService;

@Service
public class KafkaUserProducerServiceImpl implements KafkaUserProducerService {
    @SuppressWarnings("unused")
    private final Logger logger = LoggerFactory.getLogger(KafkaUserProducerServiceImpl.class);

    @Autowired
    KafkaTemplate<String, KafkaUser> kafkaTemplate;

    @Override
    public void sendUserEvent(String topicName, KafkaPayload<KafkaUser> event) {
        String key = event.getKey();
        var future = kafkaTemplate.send(topicName, key, event.getData());

        future.whenComplete((sendResult, exception) -> {
            if (exception != null) {
                future.completeExceptionally(exception);
            } else {
                future.complete(sendResult);
            }
        });
    }

}
