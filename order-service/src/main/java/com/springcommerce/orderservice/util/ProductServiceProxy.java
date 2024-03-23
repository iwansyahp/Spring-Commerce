package com.springcommerce.orderservice.util;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springcommerce.orderservice.dto.FeignPaginatedProducts;

@FeignClient(name="product-service")
public interface ProductServiceProxy {
	
	@GetMapping("/products/active")
	FeignPaginatedProducts getActiveProducts(@RequestParam("page") int page, @RequestParam("size") int size);
}
