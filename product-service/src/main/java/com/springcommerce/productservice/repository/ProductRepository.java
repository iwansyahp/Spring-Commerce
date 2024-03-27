package com.springcommerce.productservice.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.springcommerce.productservice.entity.Product;

@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {
	Page<Product> findAllByActiveOrderByNameAsc(boolean active, Pageable pageable);
	Page<Product> findAll(Pageable pageable);
	Optional<Product> findByUuid(UUID uuid);
	@Modifying
	@Query(value = "update Products set deleted = true where uuid = :uuid", nativeQuery = true)
	void deleteByUuid(@Param("uuid") UUID uuid);
}
