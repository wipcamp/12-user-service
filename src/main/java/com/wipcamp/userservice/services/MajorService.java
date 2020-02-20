package com.wipcamp.userservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.wipcamp.userservice.controllers.MajorController;
import com.wipcamp.userservice.models.Major;
import com.wipcamp.userservice.repositories.MajorRepository;
import com.wipcamp.userservice.utils.FailureResponse;
import com.wipcamp.userservice.utils.LoggerUtility;
import com.wipcamp.userservice.utils.ResponseForm;
import com.wipcamp.userservice.utils.SuccessResponse;

@Service
public class MajorService {
	@Autowired
	MajorRepository majorRepository;

	private Logger logger = LoggerFactory.getLogger(MajorController.class);

	public ResponseForm getAllMajor(HttpServletRequest request) {
		ResponseForm result = new FailureResponse();

		List<Major> allMajor = this.majorRepository.findAll();

		if (allMajor.isEmpty()) {
			LoggerUtility.logError(logger, "Cannot get all majors. No Major found in database.", "getAllMajor");
			((FailureResponse) result).setError("No Major found in database.");
		} else {
			LoggerUtility.logSuccessInfo(logger, "Successful get all majors from database.", "getAllMajor");
			result = new SuccessResponse<Major>(allMajor);
		}
		return result;
	}

	public ResponseForm getMajorByMajorId(long majorId, HttpServletRequest request) {
		ResponseForm result = new FailureResponse();

		try {
			Major currentMajor = this.majorRepository.findById(majorId).get();
			ArrayList<Major> major = new ArrayList<>();
			major.add(currentMajor);
			LoggerUtility.logSuccessInfo(logger, "Successful get major from database. majorId=" + majorId, "getMajorByMajorId");
			result = new SuccessResponse<Major>(HttpStatus.OK, major);
		} catch (NoSuchElementException ex) {
			LoggerUtility.logError(logger, "Cannot get major from database. majorId=" + majorId, "getMajorByMajorId");
			((FailureResponse) result).setError("Major not found");
		}
		return result;
	}
}
