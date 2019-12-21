package com.wipcamp.userservice.controllers;

import com.wipcamp.userservice.services.MajorService;

import com.wipcamp.userservice.utils.ResponseForm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin("${CROSSSITEDOMAIN}")
@RestController
@RequestMapping("/major")
public class MajorController {
	@Autowired
	MajorService majorService;

	Logger logger = LoggerFactory.getLogger(MajorController.class);

	//get all major when mapping major without majorId and send status found when size > 0
//	@GetMapping("")
//	public ResponseEntity<List<Major>> getAllMajor(HttpServletRequest request){
//		try{
//			List<Major> allMajor = majorService.getAllMajor(request);
//			return new ResponseEntity<List<Major>>(allMajor,HttpStatus.FOUND);
//		} catch (NoSuchElementException ex){
//				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}

	@GetMapping("")
	public ResponseEntity<ResponseForm> getAllMajor(HttpServletRequest request){
		ResponseForm result = majorService.getAllMajor(request);
		return new ResponseEntity<>(result, result.getHttpCode());
	}

	//Get single major when mapping major_id , return status found when major_id match in database
//	@GetMapping("/{majorId}")
//	public ResponseEntity<Major> getMajorByMajorId(@PathVariable("majorId") Long majorId , HttpServletRequest request){
//			Major currentMajor = majorService.findMajorById(majorId , request);
//			if(currentMajor == null){
//				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//			} else{
//				return new ResponseEntity<Major>(currentMajor,HttpStatus.FOUND);
//			}
//	}
	@GetMapping("/{majorId}")
	public ResponseEntity<ResponseForm> getMajorByMajorId(@PathVariable("majorId") long majorId , HttpServletRequest request){
		ResponseForm result = majorService.getMajor(majorId , request);
		return new ResponseEntity<>(result,result.getHttpCode());
	}
}
