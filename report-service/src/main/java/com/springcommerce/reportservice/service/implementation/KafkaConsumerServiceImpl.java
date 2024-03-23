package com.springcommerce.reportservice.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.springcommerce.reportservice.kafka.KafkaConstants;
import com.springcommerce.reportservice.kafka.KafkaOrderMessage;
import com.springcommerce.reportservice.mongodb.document.Order;
import com.springcommerce.reportservice.service.KafkaConsumerService;
import com.springcommerce.reportservice.service.MongoService;

@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

	private Logger logger = LoggerFactory.getLogger(KafkaConsumerServiceImpl.class);

	@Autowired
	MongoService mongoService;
	
	@Override
	@KafkaListener(topics = {KafkaConstants.ORDER_TOPIC}, containerFactory = "kafkaListenerContainerFactory", groupId = KafkaConstants.GROUP_ID)
	public void consumeOrderTopic(KafkaOrderMessage event) {
		
		var key = event.getKey();
		Order order = new Order(
				event.getOrder().getId().toString(),
				event.getOrder().getOrderName(),
				event.getOrder().getTotalPay(),
				event.getOrder().getStatus(),
				event.getOrder().getCreatedAt(),event.getOrder().getUpdatedAt()
		);
		switch (key) {
		case KafkaConstants.ORDER_CHECK_OUT: {
			logger.info("order is checked out");
			mongoService.storeOrder(order);
			break;
		}
		case KafkaConstants.ORDER_PAY: {
			logger.info("order is paid");
			mongoService.storeOrder(order);
			break;
		}
		case KafkaConstants.ORDER_CANCEL: {
			logger.info("order is cancelled");
			mongoService.storeOrder(order);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + key);
		}
	}
}
