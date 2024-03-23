package com.springcommerce.orderservice.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.springcommerce.orderservice.kafka.KafkaOrderMessage;
import com.springcommerce.orderservice.service.KafkaProducerService;

@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {
	@SuppressWarnings("unused")
	private final Logger logger = LoggerFactory.getLogger(KafkaProducerServiceImpl.class);

	  @Autowired
	  KafkaTemplate<String, KafkaOrderMessage> kafkaTemplate;

	  @Override
	  public void sendOrderMessage(String topicName, KafkaOrderMessage event) {

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
