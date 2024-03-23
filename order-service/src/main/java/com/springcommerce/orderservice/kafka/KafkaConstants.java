package com.springcommerce.orderservice.kafka;

public class KafkaConstants {	
	public static final String PRODUCT_TOPIC = "product-topic";
	public static final String PRODUCT_DELETE_KEY = "product_deleted";
	public static final String PRODUCT_CREATE_KEY = "product_created";
	public static final String PRODUCT_UPDATE_KEY = "product_updated";
	

	public static final String ORDER_TOPIC = "order-topic";
	public static final String ORDER_CHECK_OUT = "order_checkout";
	public static final String ORDER_PAY = "order_paid";
	public static final String ORDER_CANCEL = "order_cancelled";
	

	public static final String GROUP_ID = "order-service-group";
}
