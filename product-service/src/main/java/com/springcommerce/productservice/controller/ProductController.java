package com.springcommerce.productservice.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springcommerce.productservice.dto.CreateUpdateProductRequest;
import com.springcommerce.productservice.dto.PaginatedResponse;
import com.springcommerce.productservice.dto.ProductResponse;
import com.springcommerce.productservice.dto.Response;
import com.springcommerce.productservice.entity.Product;
import com.springcommerce.productservice.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping("/active")
	public ResponseEntity<PaginatedResponse<Product>> getProducts(
			@RequestParam(name="page", defaultValue = "1") int page,
			@RequestParam(name="size", defaultValue = "5") int size) {

		try {
			Page<Product> products = productService.findAllByActiveOrderByNameAsc(true, PageRequest.of(page, size));

			PaginatedResponse<Product> response = new PaginatedResponse<>(
					products.getNumber(),
					products.getTotalPages(),
					products.getTotalElements(),
					products.getContent()
			);
			return ResponseEntity.ok(response);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{uuid}")
	public ResponseEntity<Response<ProductResponse>> updateProduct(@RequestBody CreateUpdateProductRequest request, @PathVariable UUID uuid) {
		var response = productService.update(uuid, request);
		return response;
	}

	@DeleteMapping("/{uuid}")
	public ResponseEntity<Response<ProductResponse>> deleteProduct(@PathVariable UUID uuid) {
		var response = productService.deleteProduct(uuid);
		return response;
	}

	@PostMapping
	public ResponseEntity<Response<Product>> createProduct(@RequestBody CreateUpdateProductRequest request) {
		var response = productService.create(request);
		return response;
	}

	@PutMapping("/sync")
	public ResponseEntity<Response<String>> syncProducts() {
		var response = productService.syncProducts();
		return response;
	}
}
