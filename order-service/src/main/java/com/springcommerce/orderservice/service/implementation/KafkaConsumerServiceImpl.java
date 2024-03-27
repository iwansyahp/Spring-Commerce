package com.springcommerce.orderservice.service.implementation;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.springcommerce.events.KafkaServiceGroups;
import com.springcommerce.events.ProductEvents;
import com.springcommerce.events.UserEvents;
import com.springcommerce.events.payload.KafkaProduct;
import com.springcommerce.events.payload.KafkaUser;
import com.springcommerce.orderservice.entity.Product;
import com.springcommerce.orderservice.entity.User;
import com.springcommerce.orderservice.service.AsyncProductService;
import com.springcommerce.orderservice.service.AsyncUserService;
import com.springcommerce.orderservice.service.KafkaConsumerService;
import com.springcommerce.orderservice.util.ProductUtils;
import com.springcommerce.orderservice.util.UserUtils;

@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

	private Logger logger = LoggerFactory.getLogger(KafkaConsumerServiceImpl.class);

	@Autowired
	AsyncProductService asyncProductService;

	@Autowired
	AsyncUserService asyncUserService;

	@Override
	@KafkaListener(
		topics = { ProductEvents.PRODUCT_TOPIC },
			containerFactory = "kafkaProductListenerContainerFactory",
		groupId = KafkaServiceGroups.ORDER_SERVICE_GROUP_ID)
	public void consumeProductTopic(ConsumerRecord<String, KafkaProduct> event) {

		var key = event.key();
		Product product = ProductUtils.kafkaProductToEntityProduct(event.value());
		switch (key) {
		case ProductEvents.PRODUCT_DELETE_KEY: {
			asyncProductService.deleteProduct(product);
			logger.info("product is deleted");
			break;
		}
		case ProductEvents.PRODUCT_UPDATE_KEY: {
			asyncProductService.update(product);
			logger.info("product is updated");
			break;
		}
		case ProductEvents.PRODUCT_CREATE_KEY: {
			logger.info("product is created");
			asyncProductService.upsert(product);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + key);
		}
	}

	@Override
	@KafkaListener(
			topics = { UserEvents.USER_TOPIC },
			containerFactory = "kafkaUserListenerContainerFactory",
		groupId = KafkaServiceGroups.ORDER_SERVICE_GROUP_ID)
	public void consumeUserTopic(ConsumerRecord<String, KafkaUser> event) {

		var key = event.key();
		User user = UserUtils.toEntityUser(event.value());
		switch (key) {
		case UserEvents.USER_DELETE_KEY: {
			asyncUserService.delete(user);
			logger.info("user is deleted");
			break;
		}
		case UserEvents.USER_UPDATE_KEY: {
			asyncUserService.upsert(user);
			logger.info("user is updated");
			break;
		}
		case UserEvents.USER_CREATE_KEY: {
			logger.info("user is created");
			asyncUserService.upsert(user);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + key);
		}
	}
}
