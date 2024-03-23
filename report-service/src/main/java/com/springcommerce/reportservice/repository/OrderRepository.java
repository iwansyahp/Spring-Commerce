package com.springcommerce.reportservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.springcommerce.reportservice.mongodb.document.Order;

public interface OrderRepository extends MongoRepository<Order, String> {}
