package com.springcommerce.orderservice.util;

import com.springcommerce.events.payload.KafkaUser;
import com.springcommerce.orderservice.entity.User;

public class UserUtils {

    public static User toEntityUser(KafkaUser kafkaUser) {
        return new User(kafkaUser.uuid(), kafkaUser.email(), kafkaUser.firstName(), kafkaUser.lastName());
    }
}
