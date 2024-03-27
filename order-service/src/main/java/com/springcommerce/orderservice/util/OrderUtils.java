package com.springcommerce.orderservice.util;

import com.springcommerce.events.payload.KafkaOrder;
import com.springcommerce.orderservice.entity.Order;

public class OrderUtils {

    public static KafkaOrder entityProductToKafkaProduct(Order order) {
        return new KafkaOrder(order.getId(), order.getOrderName(), order.getTotalPay(), order.getStatus().toString(),
                order.getCreatedAt(), order.getUpdatedAt());
        }
}
