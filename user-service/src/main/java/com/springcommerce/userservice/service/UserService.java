package com.springcommerce.userservice.service;

import com.springcommerce.userservice.dto.UserDTO;
import com.springcommerce.userservice.entity.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<User> create(Mono<UserDTO> userDto);
    Mono<User> get(long userId);
    Mono<User> update(long userId, Mono<UserDTO> userDTO);
    Mono<Void> delete(long userId);
    Flux<User> list();
}
