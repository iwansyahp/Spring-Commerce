package com.springcommerce.userservice.util;

import com.springcommerce.events.payload.KafkaUser;
import com.springcommerce.userservice.dto.UserDTO;
import com.springcommerce.userservice.entity.User;

public class UserUtils {
    public static User toUser(UserDTO userDTO) {
        return new User(userDTO.email(), userDTO.firstName(), userDTO.lastName());
    }

    public static UserDTO toUser(User user) {
        return new UserDTO(user.getEmail(), user.getFirstName(), user.getLastName());
    }

    public static KafkaUser toKafkaUser(User user) {
        return new KafkaUser(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName());
    }
}
