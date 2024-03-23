package com.springcommerce.orderservice.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.springcommerce.orderservice.entity.Product;

@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findAllByUuidIn(List<UUID> uuids);
	@Modifying
	@Query(value = "update Product set deleted = true where uuid = :uuid", nativeQuery = true)
	void deleteByUuid(@Param("uuid") UUID uuid);
	Optional<Product> findByUuid(UUID uuid);
}
