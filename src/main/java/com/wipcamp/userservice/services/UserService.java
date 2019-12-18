package com.wipcamp.userservice.services;

import com.wipcamp.userservice.models.User;
import com.wipcamp.userservice.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	public User addUser(User newUser) { return userRepository.save(newUser); }
}
