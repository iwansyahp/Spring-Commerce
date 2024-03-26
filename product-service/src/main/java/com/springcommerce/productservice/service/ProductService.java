package com.springcommerce.productservice.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.springcommerce.productservice.dto.CreateUpdateProductRequest;
import com.springcommerce.productservice.dto.ProductResponse;
import com.springcommerce.productservice.dto.Response;
import com.springcommerce.productservice.entity.Product;

public interface ProductService {
	Page<Product> findAllByActiveOrderByNameAsc(boolean active, Pageable pageable);
	Page<Product> findAll(Pageable pageable);
	Optional<Product> findProductByUuid(UUID uuid);
	void saveAll(List<Product> products);
	ResponseEntity<Response<Product>> create(CreateUpdateProductRequest request);
	ResponseEntity<Response<ProductResponse>> update(UUID uuid, CreateUpdateProductRequest request);

	ResponseEntity<Response<ProductResponse>> deleteProduct(UUID uuid);
}
