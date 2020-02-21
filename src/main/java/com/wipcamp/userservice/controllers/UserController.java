package com.wipcamp.userservice.controllers;

import com.wipcamp.userservice.models.GeneralAnswer;
import com.wipcamp.userservice.models.User;
import com.wipcamp.userservice.requests.StoreUserRequest;
import com.wipcamp.userservice.requests.UpdateUserStatusRequest;
import com.wipcamp.userservice.services.UserService;

import com.wipcamp.userservice.utils.ResponseForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.Date;
import java.util.Optional;

@CrossOrigin("${CROSSSITEDOMAIN}")
@RestController
public class UserController {
	@Autowired
	UserService userService;

	@PostMapping("/user")
	public ResponseEntity<ResponseForm> createUser(HttpServletRequest request,@RequestBody StoreUserRequest storeUserRequest) {
		ResponseForm result = userService.createUser(request,storeUserRequest);
		return new ResponseEntity<>(result, result.getHttpCode());
	}

	@GetMapping("/users")
	@ResponseBody
	public ResponseEntity<ResponseForm> getAllUser(@RequestHeader(name = "Authorization", required = true) String token,@RequestParam(required=false,name="filter") String filter, @RequestParam(required=false,name="option") String option,@RequestParam(required=false,name="date") String date, HttpServletRequest request){
		ResponseForm result = userService.getAllUser(token,filter,option,date,request);
		return new ResponseEntity<>(result, result.getHttpCode());
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<ResponseForm> getUserByUserId(@RequestHeader(name = "Authorization", required = true) String token,@PathVariable("userId") long userId, HttpServletRequest request) {
		ResponseForm result = userService.getUserByUserId(token,userId, request);
		return new ResponseEntity<>(result, result.getHttpCode());
	}

	@PutMapping("/user/{userId}")
	public ResponseEntity<ResponseForm> updateUserInformation(@RequestHeader(name = "Authorization", required = true) String token,@PathVariable("userId") long userId, @Valid @RequestBody @NotNull User user,
			BindingResult theBindingResult, ModelMap model) {
		ResponseForm result = userService.updateUserCheck(token, user, userId);
		return new ResponseEntity<ResponseForm>(result, result.getHttpCode());
	}

	@PostMapping("/user/{userId}/general")
	public ResponseEntity<ResponseForm> updateUserGeneralAnswer(@RequestHeader(name = "Authorization", required = true) String token,@PathVariable("userId") long userId, @Valid @RequestBody GeneralAnswer generalAnswer) {
		ResponseForm result = userService.updateUserGeneralAnswer(token, generalAnswer, userId);
		return new ResponseEntity<ResponseForm>(result, result.getHttpCode());
	}

	@PostMapping("/user/{userId}/status")
	public ResponseEntity<ResponseForm> updateUserStatus(@RequestHeader(name = "Authorization", required = true) String token,@PathVariable("userId") long userId,
			@RequestBody @Valid UpdateUserStatusRequest userStatusRequest){
		ResponseForm result = userService.updateUserStatue(token,userStatusRequest ,userId);
		return new ResponseEntity<ResponseForm>(result,result.getHttpCode());
	}

	@PostMapping("/user/{userId}/uploadDocument")
	public ResponseEntity<ResponseForm> uploadDocument(@RequestHeader(name = "Authorization", required = true) String token,@PathVariable("userId") long userId,
			@RequestParam("file") MultipartFile file){
		ResponseForm result = userService.uploadDocument(token, file ,userId);
		return new ResponseEntity<ResponseForm>(result,result.getHttpCode());
	}

	@GetMapping("/user/{userId}/uploadDocument")
	public ResponseEntity<ResponseForm> getUploadDocument(@RequestHeader(name = "Authorization", required = true) String token,@PathVariable("userId") long userId){
		ResponseForm result = userService.getUploadDocumentCheck(token,userId);
		return new ResponseEntity<ResponseForm>(result,result.getHttpCode());
	}

	@GetMapping("/user/line/{lineId}")
	public ResponseEntity<ResponseForm> getUserByLineId(@RequestHeader(name = "Authorization", required = true) String token,@PathVariable("lineId") String lineId , HttpServletRequest request){
		ResponseForm result = userService.getUserByLineId(token,lineId , request);
		return new ResponseEntity<ResponseForm>(result,result.getHttpCode());
	}

	@GetMapping("/me")
	public ResponseEntity<ResponseForm> getUserByToken(@RequestHeader(name = "Authorization", required = true) String token , HttpServletRequest request){
		ResponseForm result = userService.getUserByToken(token , request);
		return new ResponseEntity<ResponseForm>(result,result.getHttpCode());
	}

	@PutMapping("/me")
	public ResponseEntity<ResponseForm> updateUserByToken(@RequestHeader(name = "Authorization", required = true) String token,@Valid @RequestBody @NotNull User user){
		ResponseForm result = userService.updateUserByToken(token, user);
		return new ResponseEntity<ResponseForm>(result,result.getHttpCode());
	}

	@PostMapping("/me/general")
	public ResponseEntity<ResponseForm> updateUserGeneralAnswerByToken(@RequestHeader(name = "Authorization", required = true) String token
			, @Valid @RequestBody GeneralAnswer generalAnswer) {
		ResponseForm result = userService.updateUserGeneralAnswerByToken(generalAnswer, token);
		return new ResponseEntity<ResponseForm>(result, result.getHttpCode());
	}

	@PostMapping("/me/status")
	public ResponseEntity<ResponseForm> updateUserStatusByToken(@RequestHeader(name = "Authorization", required = true) String token,
			@RequestBody @Valid UpdateUserStatusRequest userStatusRequest){
		ResponseForm result = userService.updateUserStatueByToken(userStatusRequest ,token);
		return new ResponseEntity<ResponseForm>(result,result.getHttpCode());
	}
	@PostMapping("/me/uploadDocument")
	public ResponseEntity<ResponseForm> uploadDocumentByToken(@RequestHeader(name = "Authorization", required = true) String token,
			@RequestParam("file") MultipartFile file){
		ResponseForm result = userService.uploadDocumentByToken(file, token);
		return new ResponseEntity<ResponseForm>(result,result.getHttpCode());
	}

	@GetMapping("/me/uploadDocument")
	public ResponseEntity<ResponseForm> getUploadDocument(@RequestHeader(name = "Authorization", required = true) String token){
		ResponseForm result = userService.getUploadDocumentByToken(token);
		return new ResponseEntity<ResponseForm>(result,result.getHttpCode());
	}
}

