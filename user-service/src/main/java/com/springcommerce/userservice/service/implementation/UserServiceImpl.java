package com.springcommerce.userservice.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.springcommerce.events.UserEvents;
import com.springcommerce.events.payload.KafkaPayload;
import com.springcommerce.events.payload.KafkaUser;
import com.springcommerce.userservice.dto.UserDTO;
import com.springcommerce.userservice.entity.User;
import com.springcommerce.userservice.repository.UserRepository;
import com.springcommerce.userservice.service.KafkaUserProducerService;
import com.springcommerce.userservice.service.UserService;
import com.springcommerce.userservice.util.UserUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    KafkaUserProducerService kafkaUserProducerService;

    @Override
    public Mono<User> create(Mono<UserDTO> userDto) {
        return userDto.flatMap(dto -> {
            User user = UserUtils.toUser(dto);
             // Check if the email is already registered
            Mono<Boolean> emailExistsMono = userRepository.existsByEmail(user.getEmail());

            // Chain the check with saving the user
            return emailExistsMono.flatMap(emailExists -> {
                if (emailExists) {
                    // If email exists, return an error message or handle the case appropriately
                    return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already registered"));
                } else {
                    // If email does not exist, save the user to the database
                    return userRepository.save(user)
                        .doOnSuccess(savedUser -> {
                            // If user is successfully saved, send Kafka event
                            var payload = new KafkaPayload<KafkaUser>(UserEvents.USER_CREATE_KEY, UserUtils.toKafkaUser(user));
                            kafkaUserProducerService.sendUserEvent(UserEvents.USER_TOPIC, payload);
                        });
                }
            });
        });
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
        return userDto.flatMap(dto -> {
            // User user = UserUtils.toUser(dto);
             // Check if the email is already registered
            Mono<User> emailExistsMono = userRepository.findByEmail(dto.email());

            // Chain the check with saving the user
            return emailExistsMono.flatMap(emailExists -> {
                /** email registered to different user */
                if (!emailExists.getEmail().equals(dto.email())) {
                    // If email exists, return an error message or handle the case appropriately
                    return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already registered"));
                } else {
                    return userRepository.findById(userId).flatMap(
                            user -> userDto.map(UserUtils::toUser)
                                    .doOnNext(u -> u.setId(userId)))
                            .flatMap(userRepository::save)
                            .doOnSuccess(savedUser -> {
                                logger.warn(savedUser.toString());
                                var payload = new KafkaPayload<KafkaUser>(UserEvents.USER_UPDATE_KEY,
                                        UserUtils.toKafkaUser(savedUser));
                                kafkaUserProducerService.sendUserEvent(UserEvents.USER_TOPIC, payload);
                });

                }
            });
        });


    }
}
