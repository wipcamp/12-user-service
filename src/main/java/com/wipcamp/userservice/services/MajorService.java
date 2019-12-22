package com.wipcamp.userservice.services;

import com.wipcamp.userservice.controllers.MajorController;
import com.wipcamp.userservice.models.Major;
import com.wipcamp.userservice.models.Question;
import com.wipcamp.userservice.models.User;
import com.wipcamp.userservice.repositories.MajorRepository;

import com.wipcamp.userservice.utils.FailureResponse;
import com.wipcamp.userservice.utils.ResponseForm;

import com.wipcamp.userservice.utils.SuccessResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MajorService {
	@Autowired
	MajorRepository majorRepository;

	private Logger logger = LoggerFactory.getLogger(MajorController.class);

	public ResponseForm getAllMajor(HttpServletRequest request) {
		ResponseForm result = new FailureResponse();

		List<Major> allMajor = this.majorRepository.findAll();

		if (allMajor.isEmpty()) {
			((FailureResponse) result).setError("No Major found in database.");
		} else{
			result = new SuccessResponse<Major>(allMajor);
		}
		return result;
	}

	public ResponseForm getMajorByMajorId(long majorId, HttpServletRequest request) {
		ResponseForm result = new FailureResponse();

		try{
			Major currentMajor = this.majorRepository.findById(majorId).get();
			logger.info(System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "Current Major is " + currentMajor.getName());
			ArrayList<Major> major = new ArrayList<>();
			major.add(currentMajor);
			result = new SuccessResponse<Major>(HttpStatus.OK, major);
		} catch(NoSuchElementException ex){
			logger.info(System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "Major not found");
			((FailureResponse) result).setError("Major not found");
		}
		return result;
	}
}
