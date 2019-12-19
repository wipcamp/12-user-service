package com.wipcamp.userservice.services;

import com.wipcamp.userservice.models.User;
import com.wipcamp.userservice.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	public UserService(UserRepository userRepository){
		this.userRepository = userRepository;
	}

	public User createUser(User newUser) { return userRepository.save(newUser); }

	public User updateUser(User newUser) { return userRepository.save(newUser); }

	public User findById(long i) {
		return userRepository.findById(i).get();
	}

	public List<User> getAllUser(){
		return userRepository.findAll();
	}
}
