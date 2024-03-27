package com.springcommerce.orderservice.service;

import com.springcommerce.orderservice.entity.User;

public interface AsyncUserService {
	void upsert(User user);
	void delete(User user);
}
