package com.springcommerce.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springcommerce.userservice.dto.UserDTO;
import com.springcommerce.userservice.entity.User;
import com.springcommerce.userservice.service.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    Mono<User> createUser(@RequestBody Mono<UserDTO> request) {
        return userService.create(request);
    }

    @GetMapping("/{userId}")
    Mono<ResponseEntity<User>> retrieve(@PathVariable long userId) {
        return userService.get(userId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(
                        ResponseEntity.notFound().build()
            );
    }

    @PutMapping("/{userId}")
    Mono<ResponseEntity<User>> update(@PathVariable long userId, @RequestBody Mono<UserDTO> request) {
        return userService.update(userId, request).map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{userId}")
    Mono<Void> delete(@PathVariable long userId) {
        return userService.delete(userId);
    }

    @GetMapping
    public Flux<User> list() {
        return userService.list();
    }
}
