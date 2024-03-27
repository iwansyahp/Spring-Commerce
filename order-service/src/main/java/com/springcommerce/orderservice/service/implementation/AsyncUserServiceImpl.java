package com.springcommerce.orderservice.service.implementation;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springcommerce.orderservice.entity.User;
import com.springcommerce.orderservice.repository.UserRepository;
import com.springcommerce.orderservice.service.AsyncUserService;

@Service
public class AsyncUserServiceImpl implements AsyncUserService {

	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(AsyncProductServiceImpl.class);

	@Autowired
	UserRepository userRepository;

	@Override
	public void upsert(User user) {
		Optional<User> userOptional = userRepository.findByUuid(user.getUuid());
		if (userOptional.isPresent()) {
			User userIndDb = userOptional.get();
			userIndDb.setEmail(user.getEmail());
			userIndDb.setFirstName(user.getFirstName());
			userIndDb.setLastName(user.getLastName());
			userRepository.save(userIndDb);
		} else {
			userRepository.save(user);
		}
	}

	@Override
	public void delete(User user) {
		userRepository.deleteByUuid(user.getUuid());
	}

}
