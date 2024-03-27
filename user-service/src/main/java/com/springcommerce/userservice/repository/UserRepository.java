package com.springcommerce.userservice.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.springcommerce.userservice.entity.User;

@Repository
public interface UserRepository extends R2dbcRepository<User, Long> {
}
