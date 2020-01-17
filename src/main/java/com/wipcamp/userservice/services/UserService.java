package com.wipcamp.userservice.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.wipcamp.userservice.controllers.MajorController;
import com.wipcamp.userservice.models.GeneralAnswer;
import com.wipcamp.userservice.models.User;
import com.wipcamp.userservice.repositories.AddressRepository;
import com.wipcamp.userservice.repositories.GeneralAnswerRepository;
import com.wipcamp.userservice.repositories.ParentRepository;
import com.wipcamp.userservice.repositories.UserRepository;
import com.wipcamp.userservice.utils.FailureResponse;
import com.wipcamp.userservice.utils.JwtUtility;
import com.wipcamp.userservice.utils.ResponseForm;
import com.wipcamp.userservice.utils.SuccessResponse;

import io.jsonwebtoken.Claims;

import io.jsonwebtoken.Jwt;

import io.jsonwebtoken.Jwts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

	@Autowired
	GeneralAnswerRepository generalAnswerRepository;

	@Autowired
	JwtUtility jwtUtility;

	private Logger logger = LoggerFactory.getLogger(MajorController.class);

	public ResponseForm createUser(HttpServletRequest request) {
		ResponseForm result = new FailureResponse();
		User user = new User();

		//Mock up fake line id , must change!
		Long lineId = Long.valueOf((int) Math.floor(Math.random() * 100000) + 1);
		User currentUserByLineId = userRepository.findByLineId(lineId).orElse(null);
		if(currentUserByLineId != null){
			if(currentUserByLineId.getLineId() == lineId){
				((FailureResponse) result).setError("User Exist, Cannot create new user.");
			}
		} else {
			System.out.println("LINE ID : " + lineId);
			user.setLineId(lineId);
			System.out.println("THIS IS USER : " + user.toString());
			try {
				userRepository.save(user);
				User saveUser = userRepository.findByLineId(lineId).get();
				ArrayList<User> userList = new ArrayList<>();
				userList.add(saveUser);
				result = new SuccessResponse<User>(HttpStatus.CREATED, userList);
				user = userRepository.findByLineId(lineId).get();
				logger.info(System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "Create User " + user.getWipId() + " | SUCCESS");
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
			updateUserWithNewUser(user, queryUser);

			ArrayList<User> userList = new ArrayList<>();
			userList.add(user);
			result = new SuccessResponse<User>(userList);
		}
		return result;
	}

	private void updateUserWithNewUser(User newUser, User queryUser) {
		newUser.setWipId(queryUser.getWipId());
		if (newUser.getAddress() != null) {
			if (queryUser.getAddress() != null) {
				newUser.getAddress().setId(queryUser.getAddress().getId());
			}
			addressRepository.save(newUser.getAddress());
		}
		if (newUser.getParent() != null) {
			if (queryUser.getParent() != null) {
				newUser.getParent().setId(queryUser.getParent().getId());
			}
			parentRepository.save(newUser.getParent());
		}
		userRepository.save(newUser);
	}

	public ResponseForm getUserByUserId(long userId, HttpServletRequest request) {
		ResponseForm result = new FailureResponse();

		try {
			User currentUser = this.userRepository.findById(userId).get();
			logger.info(
					System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "Current User ID : " + currentUser.getWipId());

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
		try {
			User currentUser = this.userRepository.findByLineId(lineId).get();
			logger.info(
					System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "Current Line ID : " + currentUser.getLineId());
			ArrayList<User> user = new ArrayList<>();
			user.add(currentUser);
			result = new SuccessResponse<User>(HttpStatus.OK, user);
		} catch (NoSuchElementException ex) {
			logger.info(System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "Find By Line ID , User not found");
		}
		return result;
	}

	public ResponseForm getUserByToken(String token, HttpServletRequest request) {
		ResponseForm result = new FailureResponse();
		String wipid = jwtUtility.getClaimFromToken(token, "wipid");
		try {
			//waiting for decode tokens --> token contain wipid
			//must receive header before use this method
			User currentUser = userRepository.findById(Long.valueOf(wipid)).get();
			logger.info(
					System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "Current User ID : " + currentUser.getWipId());

			ArrayList<User> user = new ArrayList<>();
			user.add(currentUser);
			result = new SuccessResponse<User>(HttpStatus.OK, user);
		} catch (NullPointerException ex) {
			logger.info(System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "Cannot get WipId from JWT Token");
		} catch (Exception ex) {
			logger.info(System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "Find By WIP ID , User not found");
		}
		return result;
	}

	public ResponseForm updateUserByToken(String token, User user) {
		String wipid = null;
		try {
			wipid = jwtUtility.getClaimFromToken(token, "wipid");
		} catch (NullPointerException e) {
			logger.info(System.currentTimeMillis() + " | Cannot get WipId from JWT Token");
		}
		return this.updateUser(user, Long.parseLong(wipid));
	}

	public ResponseForm getAllUser(String filter, String option, HttpServletRequest request) {
		ResponseForm result = new FailureResponse();
			List<User> allUser = userRepository.findAll();
			if(option.isEmpty()){
				if (allUser.isEmpty()) {
					logger.info(System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "No user in database");
					((FailureResponse) result).setError("No user found in database.");
				} else {
					logger.info(System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "User size is " + allUser.size());
					result = new SuccessResponse<User>(HttpStatus.OK, allUser);
				}
			} else if(filter.equalsIgnoreCase("graph")){
				if(option.equalsIgnoreCase("daily")){
					List<List<User>> userOfWeek = getDailyUser();
					result = new SuccessResponse<List<User>>(HttpStatus.OK,userOfWeek);
				}
			}
			return result;
	}

	public ResponseForm updateUserGeneralAnswer(GeneralAnswer generalAnswer, long userId) {
		ResponseForm result = new FailureResponse();
		User queryUser = userRepository.findById(userId).orElse(null);

		if (null == queryUser) {
			((FailureResponse) result).setError("User not found");
		} else {
			if (queryUser.getGeneralAnswer() == null) {
				generalAnswerRepository.save(generalAnswer);
				queryUser.setGeneralAnswer(generalAnswer);
				userRepository.save(queryUser);
				queryUser = userRepository.findById(queryUser.getWipId()).get();
			} else {
				generalAnswer.setId(queryUser.getGeneralAnswer().getId());
				generalAnswerRepository.save(generalAnswer);
			}
			ArrayList<User> resultData = new ArrayList<>();
			resultData.add(queryUser);
			result = new SuccessResponse<>(resultData);
		}
		return result;
	}

	public ResponseForm updateUserStatue(String status, long userId) {
		ResponseForm result = new FailureResponse();
		User queryUser = userRepository.findById(userId).orElse(null);
		return updateUserStatus(status, result, queryUser);
	}

	public ResponseForm updateUserStatueByToken(String status, String token) {
		ResponseForm result = new FailureResponse();
		String wipid = jwtUtility.getClaimFromToken(token, "wipid");
		User queryUser = userRepository.findById(Long.valueOf(wipid)).orElse(null);
		return updateUserStatus(status, result, queryUser);
	}

	private ResponseForm updateUserStatus(String status, ResponseForm result, User queryUser) {
		if(queryUser == null){
			((FailureResponse) result).setError("User not found");
		}else{
			queryUser.setStatus(status);
			userRepository.save(queryUser);
			User[] resultData = {queryUser};
			result = new SuccessResponse<User>(Arrays.asList(resultData));
		}
		return result;
	}

	public List<List<User>> getDailyUser(){
		LocalDate previousDate = LocalDate.now().minusDays(7);
		List<List<User>> userOfWeek = new ArrayList<>();
		for (int i = 1 ; i <= 7 ; i++){
			Date thisDate = Date.valueOf(previousDate.plusDays(i));
			List<User> currentUserOfDate = userRepository.findByCreatedAt(thisDate);
			userOfWeek.add(currentUserOfDate);
		}
		return userOfWeek;
	}
}
