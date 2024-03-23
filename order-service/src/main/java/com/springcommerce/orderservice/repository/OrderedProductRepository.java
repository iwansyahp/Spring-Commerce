package com.springcommerce.orderservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springcommerce.orderservice.entity.OrderedProduct;

public interface OrderedProductRepository extends JpaRepository<OrderedProduct, UUID> {
	List<OrderedProduct> findAllByOrderId(UUID id);
}
