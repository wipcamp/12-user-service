package com.wipcamp.userservice.services;

import com.wipcamp.userservice.controllers.MajorController;
import com.wipcamp.userservice.models.Address;
import com.wipcamp.userservice.models.Answer;
import com.wipcamp.userservice.models.Major;
import com.wipcamp.userservice.models.Parent;
import com.wipcamp.userservice.models.User;
import com.wipcamp.userservice.repositories.AddressRepository;
import com.wipcamp.userservice.repositories.ParentRepository;
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

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	ParentRepository parentRepository;

	@Autowired
	AddressRepository addressRepository;

	private Logger logger = LoggerFactory.getLogger(MajorController.class);

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public ResponseForm createUser(HttpServletRequest request) {
		ResponseForm result = new FailureResponse();
		User user = new User();


		//Mock up fake line id , must change!
		Long lineId = Long.valueOf((int) Math.floor(Math.random() * 100000) + 1);


		if (userRepository.findByLineId(lineId) != null) {
			if (userRepository.findByLineId(lineId).getLineId() == lineId) {
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
						System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "Create User " + user.getWipId() + " | SUCCESS");
			} catch (Exception ex) {
				logger.info(System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "Cannot create user in database.");
				((FailureResponse) result).setError("Cannot create user in database.");

			}
		}
		return result;
	}

	public ResponseForm updateUser(User user, long userId) {
		User queryUser = userRepository.findById(userId).orElse(null);
		ResponseForm result = new FailureResponse();
		if (queryUser == null) {
			((FailureResponse) result).setError("User not found");
		} else {
			user.setWipId(queryUser.getWipId());
			if (user.getAddress() != null) {
				if(queryUser.getAddress() != null){
					user.getAddress().setId(queryUser.getAddress().getId());
				}
					addressRepository.save(user.getAddress());
			}
			if (user.getParent() != null) {
				if(queryUser.getParent() != null){
					user.getParent().setId(queryUser.getParent().getId());
				}
				parentRepository.save(user.getParent());
			}
			userRepository.save(user);

			ArrayList<User> userList = new ArrayList<>();
			userList.add(user);
			result = new SuccessResponse<User>(userList);
		}
		return result;
	}

	public ResponseForm getUserByUserId(long userId, HttpServletRequest request) {
		ResponseForm result = new FailureResponse();

		try {
			User currentUser = this.userRepository.findById(userId).get();
			logger.info(System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "Current User ID : " + currentUser.getWipId());

			ArrayList<User> user = new ArrayList<>();
			user.add(currentUser);
			result = new SuccessResponse<User>(HttpStatus.OK, user);
		} catch (NoSuchElementException ex) {
			logger.info(System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "User not found");
		}
		return result;
	}

	public ResponseForm getUserByLineId(long lineId, HttpServletRequest request) {
		ResponseForm result = new FailureResponse();
		try{
			User currentUser = this.userRepository.findByLineId(lineId);
			logger.info(System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "Current Line ID : " + currentUser.getLineId());
			ArrayList<User> user = new ArrayList<>();
			user.add(currentUser);
			result = new SuccessResponse<User>(HttpStatus.OK , user);
		} catch(NoSuchElementException ex){
			logger.info(System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "Find By Line ID , User not found");
		}
		return result;
	}

	public ResponseForm getUserByToken(String token , HttpServletRequest request) {
		ResponseForm result = new FailureResponse();
		try{
			//waiting for decode tokens --> token contain wipid
			long mockup_wipid = 111111;
			User currentUser = userRepository.findByWipId(mockup_wipid);
			logger.info(System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "Current User ID : " + currentUser.getWipId());

			ArrayList<User> user = new ArrayList<>();
			user.add(currentUser);
			result = new SuccessResponse<User>(HttpStatus.OK, user);
		} catch(Exception ex){
			logger.info(System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "Find By WIP ID , User not found");
		}
		return result;
	}

	public ResponseForm updateUserByToken(User user) {
		User queryUser = userRepository.findById((long) 1).orElse(null);
		ResponseForm result = new FailureResponse();
		if (queryUser == null) {
			((FailureResponse) result).setError("User not found");
		} else {
			user.setWipId(queryUser.getWipId());
			if (user.getAddress() != null) {
				if(queryUser.getAddress() != null){
					user.getAddress().setId(queryUser.getAddress().getId());
				}
				addressRepository.save(user.getAddress());
			}
			if (user.getParent() != null) {
				if(queryUser.getParent() != null){
					user.getParent().setId(queryUser.getParent().getId());
				}
				parentRepository.save(user.getParent());
			}
			userRepository.save(user);

			ArrayList<User> userList = new ArrayList<>();
			userList.add(user);
			result = new SuccessResponse<User>(userList);
		}
		return result;
	}
}
