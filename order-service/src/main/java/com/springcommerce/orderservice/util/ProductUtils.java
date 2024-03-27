package com.springcommerce.orderservice.util;

import com.springcommerce.events.payload.KafkaProduct;
import com.springcommerce.orderservice.entity.Product;

public class ProductUtils {

    public static Product kafkaProductToEntityProduct(KafkaProduct kafkaProduct) {
        return new Product(kafkaProduct.uuid(), kafkaProduct.name(), kafkaProduct.price(), kafkaProduct.availability(), kafkaProduct.active(),kafkaProduct.createdData(), kafkaProduct.updatedDate());
    }
}
