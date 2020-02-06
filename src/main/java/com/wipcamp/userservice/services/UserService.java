package com.wipcamp.userservice.services;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xmlpull.v1.XmlPullParserException;

import com.wipcamp.userservice.controllers.MajorController;
import com.wipcamp.userservice.models.GeneralAnswer;
import com.wipcamp.userservice.models.User;
import com.wipcamp.userservice.models.UserStatus;
import com.wipcamp.userservice.repositories.GeneralAnswerRepository;
import com.wipcamp.userservice.repositories.ParentRepository;
import com.wipcamp.userservice.repositories.SchoolRepository;
import com.wipcamp.userservice.repositories.UserRepository;
import com.wipcamp.userservice.repositories.UserStatusRepository;
import com.wipcamp.userservice.requests.StoreUserRequest;
import com.wipcamp.userservice.requests.UpdateUserStatusRequest;
import com.wipcamp.userservice.responses.CreateUserResponse;
import com.wipcamp.userservice.responses.UserInformationResponse;
import com.wipcamp.userservice.responses.UserUpdateResponse;
import com.wipcamp.userservice.utils.FailureResponse;
import com.wipcamp.userservice.utils.JwtUtility;
import com.wipcamp.userservice.utils.ResponseForm;
import com.wipcamp.userservice.utils.SuccessResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.minio.MinioClient;
import io.minio.errors.MinioException;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ParentRepository parentRepository;

	@Autowired
	SchoolRepository schoolRepository;

	@Autowired
	GeneralAnswerRepository generalAnswerRepository;

	@Autowired
	UserStatusRepository userStatusRepository;

	@Autowired
	com.wipcamp.userservice.repositories.knowWhenceRepository knowWhenceRepository;

	@Autowired
	JwtUtility jwtUtility;

	@Value("${minio.endpoint}")
	private String MINIOENDPOINT;

	@Value("${minio.access}")
	private String MINIOACCESSKEY;

	@Value("${minio.secret}")
	private String MINIOSECRETKEY;

	@Value("${minio.bucketname}")
	private String MINIOBUCKETNAME;

	private Logger logger = LoggerFactory.getLogger(MajorController.class);

	public ResponseForm createUser(HttpServletRequest request, StoreUserRequest storeUserRequest) {
		ResponseForm result = new FailureResponse();
		String lineId = storeUserRequest.getLineId();
		User currentUserByLineId = userRepository.findByLineId(lineId).orElse(null);
		if (currentUserByLineId != null) {
			if (currentUserByLineId.getLineId().equals(lineId)) {
				Claims claims = Jwts.claims();
				claims.setSubject("master.user.service.wip.camp");
				claims.put("wipId", currentUserByLineId.getWipId());
				CreateUserResponse createUserResponse = new CreateUserResponse("User Exist", jwtUtility.generateToken(claims));

				List<CreateUserResponse> responseData = new ArrayList<>(1);
				responseData.add(createUserResponse);

				result = new SuccessResponse<CreateUserResponse>(responseData);
			}
		} else {
			User user = new User();
			user.setLineId(lineId);
			try {
				userRepository.save(user);
				User saveUser = userRepository.findByLineId(lineId).get();

				Claims claims = Jwts.claims();
				claims.setSubject("master.user.service.wip.camp");
				claims.put("wipId", saveUser.getWipId());
				CreateUserResponse createUserResponse = new CreateUserResponse("Create User", jwtUtility.generateToken(claims));

				List<CreateUserResponse> responseData = new ArrayList<>(1);
				responseData.add(createUserResponse);

				result = new SuccessResponse<CreateUserResponse>(HttpStatus.CREATED, responseData);

				logger.info(System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "Create User " + user.getWipId()
						+ " | SUCCESS");
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
		if (newUser.getParent() != null) {
			if (queryUser.getParent() != null) {
				newUser.getParent().setId(queryUser.getParent().getId());
			}
			parentRepository.save(newUser.getParent());
		}
		if (newUser.getSchool() != null) {
			if (queryUser.getSchool() != null) {
				newUser.getSchool().setId(queryUser.getSchool().getId());
			}
			schoolRepository.save(newUser.getSchool());
		}
		if (newUser.getKnowWhence() != null) {
			if (queryUser.getKnowWhence() != null) {
				newUser.getKnowWhence().setId(queryUser.getKnowWhence().getId());
			}
			knowWhenceRepository.save(newUser.getKnowWhence());
		}
		newUser.setLineId(queryUser.getLineId());
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

	public ResponseForm getUserByLineId(String lineId, HttpServletRequest request) {
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
		Integer wipId = null;
		try {
			wipId = jwtUtility.getClaimFromToken(token, "wipId");
		} catch (NullPointerException e) {
			logger.info(System.currentTimeMillis() + " | JWT doesn't have wipId Field");
		}
		try {
			User currentUser = userRepository.findById(Long.valueOf(wipId)).orElse(null);
			if (currentUser == null) {
				((FailureResponse) result).setError("User not found");
				return result;
			}
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
		Integer wipId = null;
		try {
			wipId = jwtUtility.getClaimFromToken(token, "wipId");
		} catch (NullPointerException e) {
			logger.info(System.currentTimeMillis() + " | JWT doesn't have wipId Field");
		}
		return this.updateUser(user, wipId);
	}

	public ResponseForm getAllUser(String filter, String option, String date, HttpServletRequest request) {
		ResponseForm result = new FailureResponse();
		List<User> allUser = userRepository.findAll();
		if (filter == null) {
			if (allUser == null) {
				logger.info(System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "No user in database");
				((FailureResponse) result).setError("No user found in database.");
			} else {
				logger.info(System.currentTimeMillis() + " | " + request.getRemoteAddr() + " | " + "User size is " + allUser.size());
				result = new SuccessResponse<User>(HttpStatus.OK, allUser);
			}
		} else if (filter.equalsIgnoreCase("graph")) {
			if (option.equalsIgnoreCase("daily")) {
				List<Integer> userOfWeek = getDailyUser(date);
				result = new SuccessResponse<Integer>(HttpStatus.OK, userOfWeek);
			} else if (option.equalsIgnoreCase("hourly")) {
				List<Integer> userOfDay = getHourlyUser(date);
				result = new SuccessResponse<Integer>(HttpStatus.OK, userOfDay);
			}
		} else if (filter.equalsIgnoreCase("update")) {
			UserUpdateResponse updateResponse = getUpdateCountUser();
			List<UserUpdateResponse> responseData = new ArrayList<>();
			responseData.add(updateResponse);
			result = new SuccessResponse<UserUpdateResponse>(responseData);
		} else if (filter.equalsIgnoreCase("information")) {
			UserInformationResponse userInformationResponse = getUserInformation();
			List<UserInformationResponse> responseData = new ArrayList<>();
			responseData.add(userInformationResponse);
			result = new SuccessResponse<UserInformationResponse>(responseData);
		}
		return result;
	}

	private UserInformationResponse getUserInformation() {
		int accepted = userStatusRepository.countByIsAccepted(true);
		int registered = userStatusRepository.countByIsRegistered(true);
		int generalAnswered = userStatusRepository.countByIsGeneralAnswered(true);
		int majorAnswered = userStatusRepository.countByIsMajorAnswered(true);
		int submitted = userStatusRepository.countByIsSubmitted(true);
		int total = Math.toIntExact(userStatusRepository.count());
		return new UserInformationResponse(total, accepted, registered, generalAnswered, majorAnswered, submitted);
	}

	private UserUpdateResponse getUpdateCountUser() {
		LocalDate todayDate = LocalDate.now();
		List<Integer> yesterdayUserCount = userRepository.findDailyUser(String.valueOf(todayDate.minusDays(1)));
		List<Integer> todayUserCount = userRepository.findDailyUser(String.valueOf(todayDate));
		int count = todayUserCount.size() - yesterdayUserCount.size();
		return new UserUpdateResponse(yesterdayUserCount.size(), todayUserCount.size(), (int) count);
	}

	public ResponseForm updateUserGeneralAnswer(GeneralAnswer generalAnswer, long userId) {
		User queryUser = userRepository.findById(userId).orElse(null);
		return updateGeneralAnswer(generalAnswer, queryUser);
	}

	public ResponseForm updateUserGeneralAnswerByToken(GeneralAnswer generalAnswer, String token) {
		ResponseForm result = new FailureResponse();
		Integer wipId = null;
		try {
			wipId = jwtUtility.getClaimFromToken(token, "wipId");
		} catch (NullPointerException e) {
			((FailureResponse) result).setError("Cannot get wipId from Jwt Token");
		}
		User queryUser = userRepository.findById(Long.valueOf(wipId)).orElse(null);
		result = updateGeneralAnswer(generalAnswer, queryUser);
		return result;
	}

	private ResponseForm updateGeneralAnswer(GeneralAnswer generalAnswer, User queryUser) {
		ResponseForm result = new FailureResponse();
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

	public ResponseForm updateUserStatue(UpdateUserStatusRequest userStatusRequest, long userId) {
		ResponseForm result = new FailureResponse();
		User queryUser = userRepository.findById(userId).orElse(null);
		return updateUserStatus(userStatusRequest, result, queryUser);
	}

	public ResponseForm updateUserStatueByToken(UpdateUserStatusRequest updateUserStatusRequest, String token) {
		ResponseForm result = new FailureResponse();
		Integer wipId = null;
		try {
			wipId = jwtUtility.getClaimFromToken(token, "wipId");
		} catch (NullPointerException e) {
			logger.info(System.currentTimeMillis() + " | JWT doesn't have wipId Field");
		}
		User queryUser = userRepository.findById(Long.valueOf(wipId)).orElse(null);
		return updateUserStatus(updateUserStatusRequest, result, queryUser);
	}

	private ResponseForm updateUserStatus(UpdateUserStatusRequest updateUserStatusRequest, ResponseForm result, User queryUser) {
		if (queryUser == null) {
			((FailureResponse) result).setError("User not found");
		} else {
			UserStatus userStatus;
			if (queryUser.getUserStatus() != null) {
				userStatus = queryUser.getUserStatus();
			} else {
				userStatus = new UserStatus();
			}
			switch (updateUserStatusRequest.getStatus()) {
			case "accept":
				userStatus.setAccepted(true);
				break;
			case "register":
				userStatus.setRegistered(true);
				break;
			case "general":
				userStatus.setGeneralAnswered(true);
				break;
			case "major":
				userStatus.setMajorAnswered(true);
				break;
			case "submit":
				userStatus.setSubmitted(true);
				break;
			default:
				((FailureResponse) result).setError("Status does not match the pattern");
				break;
			}
			userStatusRepository.save(userStatus);
			queryUser.setUserStatus(userStatus);
			userRepository.save(queryUser);
			User[] resultData = {queryUser};
			result = new SuccessResponse<User>(Arrays.asList(resultData));
		}

		return result;
	}

	public List<Integer> getDailyUser(String date) {
		LocalDate previousDate;
		if (date == null) {
			previousDate = LocalDate.now().minusDays(7);
		} else {
			previousDate = LocalDate.parse(date).minusDays(1);
		}
		List<Integer> userOfWeek = new ArrayList<>();
		for (int i = 1; i <= 7; i++) {
			Date thisDate = Date.valueOf(previousDate.plusDays(i));
			List<Integer> currentUserOfDate = userRepository.findDailyUser(String.valueOf(thisDate));
			userOfWeek.add(currentUserOfDate.size());
		}
		return userOfWeek;
	}

	public List<Integer> getHourlyUser(String date) {
		List<Integer> userOfDay = new ArrayList<>();
		for (int i = 1; i <= 24; i++) {
			List<User> userPerHour = userRepository.findUserPerHour(date, String.valueOf(i));
			userOfDay.add(userPerHour.size());
		}
		return userOfDay;
	}

	public ResponseForm uploadDocument(MultipartFile file, long userId) {
		return uploadDocumentToMinio(file, userId);
	}

	public ResponseForm uploadDocumentByToken(MultipartFile file, String token) {
		Integer wipid = null;
		try {
			wipid = jwtUtility.getClaimFromToken(token, "wipId");
		} catch (NullPointerException e) {
			logger.info(System.currentTimeMillis() + " | JWT doesn't have wipId Field");
		}
		return uploadDocumentToMinio(file, wipid);
	}

	private ResponseForm uploadDocumentToMinio(MultipartFile file, long userId) {
		ResponseForm result = new FailureResponse();
		User user = userRepository.findById(userId).orElse(null);
		if (user == null) {
			((FailureResponse) result).setError("User not found");
			return result;
		}
		if (file == null) {
			((FailureResponse) result).setError("File must be upload");
		} else {
			String fileType = Objects.requireNonNull(file.getOriginalFilename())
					.substring(file.getOriginalFilename().lastIndexOf('.'), file.getOriginalFilename().length());
			if (!fileType.equals(".pdf")) {
				((FailureResponse) result).setError("File type must be pdf");
			} else if (file.getSize() > 5_242_880) {
				((FailureResponse) result).setError("File size cannot larger than 5MB");
			} else {
				try {

					MinioClient minioClient = new MinioClient(MINIOENDPOINT, MINIOACCESSKEY, MINIOSECRETKEY);

					if (!minioClient.bucketExists(MINIOBUCKETNAME)) {
						minioClient.makeBucket(MINIOBUCKETNAME);
					}

					String objectName = userId + "-document" + fileType;
					HashMap<String, String> header = new HashMap<>();
					header.put("contentType", "application/octet-stream");

					minioClient.putObject(MINIOBUCKETNAME, objectName, file.getInputStream(), file.getSize(), header);

					user.setUploadDocumentPath(MINIOENDPOINT + '/' + MINIOBUCKETNAME + '/' + objectName);
					userRepository.save(user);

					ArrayList<String> resultData = new ArrayList<>(1);
					resultData.add("File " + objectName + " has been uploaded to server!");
					result = new SuccessResponse<String>(resultData);
				} catch (MinioException e) {
					((FailureResponse) result).setError("Minio Exception : " + e);
				} catch (IOException e) {
					((FailureResponse) result).setError("IO Exception : " + e);
				} catch (InvalidKeyException e) {
					((FailureResponse) result).setError("Invalid Exception : " + e);
				} catch (NoSuchAlgorithmException e) {
					((FailureResponse) result).setError("No Such Algorithm Exception : " + e);
				} catch (XmlPullParserException e) {
					((FailureResponse) result).setError("XmlPullParser Exception : " + e);
				} catch (NullPointerException e) {
					((FailureResponse) result).setError("File type must be pdf");
				}
			}
		}
		return result;
	}

}
