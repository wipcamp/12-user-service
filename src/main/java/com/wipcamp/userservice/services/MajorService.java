package com.wipcamp.userservice.services;

import com.wipcamp.userservice.controllers.MajorController;
import com.wipcamp.userservice.models.Major;
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

	Logger logger = LoggerFactory.getLogger(MajorController.class);

	public MajorService(MajorRepository majorRepository){
		this.majorRepository = majorRepository;
	}

	public Optional<Major> findByOptionalId(long userId) { return majorRepository.findById(userId); }

//	public List<Major> getAllMajor(HttpServletRequest request){
//		List<Major> allMajor = majorRepository.findAll();
//		if(allMajor.isEmpty()){
//			logger.info(System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "No major in database" );
//			return null;
//		} else{
//			logger.info(System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "Major size is " + allMajor.size());
//			return allMajor;
//		}
//	}

//	public Major findMajorById(Long majorId , HttpServletRequest request){
//		try{
//			Major currentMajor = majorRepository.findById(majorId).get();
//			logger.info(System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "Current Major is " + currentMajor.getName());
//			return currentMajor;
//		} catch(NoSuchElementException ex) {
//			logger.info(System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "Major not found");
//			return null;
//		}
//	}

	public ResponseForm getAllMajor(HttpServletRequest request) {
		ResponseForm result = new FailureResponse();

		List<Major> allMajor = this.majorRepository.findAll();

		if (allMajor.isEmpty()) {
			logger.info(System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "No major in database" );
			((FailureResponse) result).setError("No Major found in database.");
		} else{
			logger.info(System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "Major size is " + allMajor.size());
			result = new SuccessResponse<Major>(HttpStatus.OK, allMajor);
		}
		return result;
	}

	public ResponseForm getMajor(long majorId, HttpServletRequest request) {
		ResponseForm result = new FailureResponse();

		try{
			Major currentMajor = this.majorRepository.findById(majorId).get();
			logger.info(System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "Current Major is " + currentMajor.getName());
			ArrayList<Major> major = new ArrayList<>();
			major.add(currentMajor);
			result = new SuccessResponse<Major>(HttpStatus.OK, major);
		} catch(NoSuchElementException ex){
			logger.info(System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "Major not found");
		}
		return result;
	}
}
