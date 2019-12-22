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
public class MajorController {
	@Autowired
	MajorService majorService;

	@GetMapping("/majors")
	public ResponseEntity<ResponseForm> getAllMajor(HttpServletRequest request){
		ResponseForm result = majorService.getAllMajor(request);
		return new ResponseEntity<>(result, result.getHttpCode());
	}

	@GetMapping("/major/{majorId}")
	public ResponseEntity<ResponseForm> getMajorByMajorId(@PathVariable("majorId") long majorId , HttpServletRequest request){
		ResponseForm result = majorService.getMajorByMajorId(majorId , request);
		return new ResponseEntity<ResponseForm>(result,result.getHttpCode());
	}
}
