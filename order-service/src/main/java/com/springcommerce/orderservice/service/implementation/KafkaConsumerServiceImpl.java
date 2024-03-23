package com.springcommerce.orderservice.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.springcommerce.orderservice.entity.Product;
import com.springcommerce.orderservice.kafka.KafkaProductMessage;
import com.springcommerce.orderservice.service.AsyncProductService;
import com.springcommerce.orderservice.service.KafkaConsumerService;
import com.springcommerce.orderservice.kafka.KafkaConstants;

@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

	private Logger logger = LoggerFactory.getLogger(KafkaConsumerServiceImpl.class);

	@Autowired
	AsyncProductService asyncProductService;
	
	@Override
	@KafkaListener(topics = {KafkaConstants.PRODUCT_TOPIC}, containerFactory = "kafkaListenerContainerFactory", groupId = KafkaConstants.GROUP_ID)
	public void consumeProductTopic(KafkaProductMessage event) {
		
		logger.info(event.toString());
		var key = event.getKey();
		Product product = (Product) event.getProduct();
		switch (key) {
		case KafkaConstants.PRODUCT_DELETE_KEY: {
			asyncProductService.deleteProduct(product);
			logger.info("product is deleted");
			break;
		}
		case KafkaConstants.PRODUCT_UPDATE_KEY: {
			asyncProductService.update(product);
			logger.info("product is updated");
			break;
		}
		case KafkaConstants.PRODUCT_CREATE_KEY: {
			logger.info("product is created");
			asyncProductService.create(product);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + key);
		}
	}
}
