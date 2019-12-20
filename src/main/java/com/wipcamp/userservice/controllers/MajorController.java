package com.wipcamp.userservice.controllers;

import com.wipcamp.userservice.models.Major;

import com.wipcamp.userservice.services.MajorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/major")
public class MajorController {
	@Autowired
	MajorService majorService;

	Logger logger = LoggerFactory.getLogger(MajorController.class);

	@GetMapping("")
	public ResponseEntity<List<Major>> getAllMajor(HttpServletRequest request){
		try{
			List<Major> allMajor = majorService.getAllMajor();
			logger.info(System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "Major size is " + allMajor.size());
			return new ResponseEntity<List<Major>>(allMajor,HttpStatus.FOUND);
		} catch (NoSuchElementException ex){
				logger.info(System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "No major in database" );
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	//Get single major when mapping major_id , return status found when major_id match in database
	@GetMapping("/{majorId}")
	public ResponseEntity<Major> getMajorByMajorId(@PathVariable("major_id") Long majorId , HttpServletRequest request){
		try{
			Major currentMajor = majorService.findMajorById(majorId);
			logger.info(System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "Current Major is " + currentMajor.getName());
			return new ResponseEntity<Major>(currentMajor,HttpStatus.FOUND);
		} catch(NoSuchElementException ex){
			logger.info(System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "Major not found" );
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
