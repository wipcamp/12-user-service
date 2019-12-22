package com.wipcamp.userservice.services;

import com.wipcamp.userservice.controllers.MajorController;
import com.wipcamp.userservice.models.User;
import com.wipcamp.userservice.repositories.UserRepository;

import com.wipcamp.userservice.utils.FailureResponse;
import com.wipcamp.userservice.utils.ResponseForm;

import com.wipcamp.userservice.utils.SuccessResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	Logger logger = LoggerFactory.getLogger(MajorController.class);

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public ResponseForm createUser(HttpServletRequest request) {
		ResponseForm result = new FailureResponse();
		User user = new User();

		Long lineId = Long.valueOf((int) Math.floor(Math.random() * 100000) + 1);
		if (userRepository.findByLineId(lineId) != null) {
			if (userRepository.findByLineId(lineId).getId() == lineId) {
				((FailureResponse) result).setError("User Exist, Cannot create new user.");
			}
		} else {
			System.out.println("LINE ID : " + lineId);
			user.setLineId(lineId);
			System.out.println("THIS IS USER : " + user.toString());
			try {
				userRepository.save(user);
				User saveUser = userRepository.findByLineId(lineId);
				ArrayList<User> userList = new ArrayList<>();
				userList.add(saveUser);
				result = new SuccessResponse<User>(HttpStatus.CREATED, userList);
				user = userRepository.findByLineId(lineId);
				logger.info(
						System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "Create User " + user.getId() + " | SUCCESS");
			} catch (Exception ex) {
				logger.info(System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "Cannot create user in database.");
				((FailureResponse) result).setError("Cannot create user in database.");

			}
		}
		return result;
	}

	public User updateUser(User user, long userId) {
		User queryUser = userRepository.findById(userId).orElse(null);
		if (queryUser == null) {
			return null;
		} else {
			user.setId(queryUser.getId());
			return userRepository.save(user);
		}
	}

	public Optional<User> findByOptionalId(long userId) {
		return userRepository.findById(userId);
	}

	public ResponseForm getAllUser(HttpServletRequest request) {
		ResponseForm result = new FailureResponse();

		List<User> allUser = this.userRepository.findAll();

		if (allUser.isEmpty()) {
			logger.info(System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "No User in database");
			((FailureResponse) result).setError("No User found in database.");
		} else {
			logger.info(System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "User size is " + allUser.size());
			result = new SuccessResponse<User>(HttpStatus.OK, allUser);
		}
		return result;
	}

	public ResponseForm getUserByUserId(long userId, HttpServletRequest request) {
		ResponseForm result = new FailureResponse();

		try {
			User currentUser = this.userRepository.findById(userId).get();
			logger.info(
					System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "Current User ID : " + currentUser.getId());
			ArrayList<User> user = new ArrayList<>();
			user.add(currentUser);
			result = new SuccessResponse<User>(HttpStatus.OK, user);
		} catch (NoSuchElementException ex) {
			logger.info(System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "User not found");
		}
		return result;
	}
}
