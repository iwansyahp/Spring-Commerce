package com.springcommerce.orderservice.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.springcommerce.events.KafkaServiceGroups;
import com.springcommerce.events.payload.KafkaProduct;
import com.springcommerce.events.payload.KafkaUser;

@Configuration
@EnableKafka
public class KafkaConsumerConfiguration {

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;

    @Bean
    Map<String, Object> consumerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaServiceGroups.ORDER_SERVICE_GROUP_ID);
		props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.springcommerce.events.payload");

		return props;
	}

	@Bean(name = "kafkaProductConsumerFactory")
    ConsumerFactory<String, KafkaProduct> kafkaProductConsumerFactory() {
		return new DefaultKafkaConsumerFactory<>(
				consumerConfigs(),
			new StringDeserializer(),
				new JsonDeserializer<KafkaProduct>(KafkaProduct.class, false));
	}

	@Bean(name = "kafkaProductListenerContainerFactory")
	ConcurrentKafkaListenerContainerFactory<String, KafkaProduct> kafkaProductListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, KafkaProduct> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(kafkaProductConsumerFactory());

		return factory;
	}

	@Bean(name = "kafkaUserConsumerFactory")
    ConsumerFactory<String, KafkaUser> kafkaUserConsumerFactory() {
		return new DefaultKafkaConsumerFactory<>(
				consumerConfigs(),
			new StringDeserializer(),
				new JsonDeserializer<KafkaUser>(KafkaUser.class, false));
	}

	@Bean(name = "kafkaUserListenerContainerFactory")
    ConcurrentKafkaListenerContainerFactory<String,  KafkaUser> kafkaUserListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String,  KafkaUser> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(kafkaUserConsumerFactory());

		return factory;
	}
}
