package com.springcommerce.orderservice.service.implementation;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springcommerce.orderservice.entity.Product;
import com.springcommerce.orderservice.repository.ProductRepository;
import com.springcommerce.orderservice.service.AsyncProductService;

@Service
public class AsyncProductServiceImpl implements AsyncProductService {

	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(AsyncProductServiceImpl.class);

	@Autowired
	ProductRepository productRepository;

	@Override
	public void upsert(Product product) {
		Optional<Product> productFromDb = productRepository.findByUuid(product.getUuid());
		if (productFromDb.isPresent()) {
			this.update(product);
		} else {
			productRepository.save(product);
		}
	}

	@Override
	public void update(Product product) {
		Optional<Product> productFromDb = productRepository.findByUuid(product.getUuid());

		if (productFromDb.isPresent()) {
			var updateProduct = productFromDb.get();
			updateProduct.setName(product.getName());
			updateProduct.setPrice(product.getPrice());
			updateProduct.setAvailability(product.getAvailability());
			updateProduct.setActive(product.isActive());
			productRepository.save(updateProduct);
		}
	}

	@Override
	public void deleteProduct(Product product) {
		Optional<Product> productFromDb = productRepository.findByUuid(product.getUuid());
		if (productFromDb.isPresent()) {
			productRepository.deleteByUuid(product.getUuid());
		}
	}
}
