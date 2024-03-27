package com.springcommerce.productservice.util;

import com.springcommerce.events.payload.KafkaProduct;
import com.springcommerce.productservice.entity.Product;

public class ProductUtils {
    public static KafkaProduct productEntityToKafkaPayload(Product product) {
        return new KafkaProduct(product.getId(), product.getUuid(), product.getName(), product.getPrice(),
                product.getAvailability(), product.isActive(), product.getCreatedAt(), product.getUpdatedAt(), product.isDeleted());
    }
}
