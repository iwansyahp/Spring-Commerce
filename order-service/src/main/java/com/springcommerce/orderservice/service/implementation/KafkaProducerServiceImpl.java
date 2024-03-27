package com.springcommerce.orderservice.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.springcommerce.events.payload.KafkaOrder;
import com.springcommerce.events.payload.KafkaPayload;
import com.springcommerce.orderservice.service.KafkaProducerService;

@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {
	@SuppressWarnings("unused")
	private final Logger logger = LoggerFactory.getLogger(KafkaProducerServiceImpl.class);

	  @Autowired
	  KafkaTemplate<String, KafkaPayload<KafkaOrder>> kafkaTemplate;

	  @Override
	  public void sendOrderMessage(String topicName, KafkaPayload<KafkaOrder> event) {

		String key = event.getKey();
	    var future = kafkaTemplate.send(topicName, key, event);

	    future.whenComplete((sendResult, exception) -> {
	      if (exception != null) {
	        future.completeExceptionally(exception);
	      } else {
	        future.complete(sendResult);
	      }
	    });
	  }
}
