package com.springcommerce.userservice.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.springcommerce.userservice.entity.User;

public interface UserRepository extends ReactiveCrudRepository<User, Long>{}
