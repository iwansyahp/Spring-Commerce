package com.springcommerce.userservice.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springcommerce.userservice.dto.UserDTO;
import com.springcommerce.userservice.entity.User;
import com.springcommerce.userservice.repository.UserRepository;
import com.springcommerce.userservice.service.UserService;
import com.springcommerce.userservice.util.UserUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<User> create(Mono<UserDTO> userDto) {
        return userDto
            .map(UserUtils::toUser)
            .flatMap(userRepository::save);
    }

    @Override
    public Mono<Void> delete(long userId) {
        return userRepository.deleteById(userId);
    }

    @Override
    public Mono<User> get(long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Flux<User> list() {
        return userRepository.findAll();
    }

    @Override
    public Mono<User> update(long userId, Mono<UserDTO> userDto) {
        return userRepository.findById(userId).flatMap(
                user -> userDto.map(
                        UserUtils::toUser).doOnNext(
                                u -> u.setId(userId)))
                .flatMap(userRepository::save);
    }
}
