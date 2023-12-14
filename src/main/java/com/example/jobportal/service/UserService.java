package com.example.jobportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.jobportal.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;
	
	private User convertToUser(UserRequestDto userRq ,User user)
	{
	
		user.setUserName(userRq.getUserName());
		user.setDateOfBirth(userRq.getDateOfBirth());
		user.setUserEmail(userRq.getUserEmail());
		user.setUserPassword(userRq.getUserPassword());
		return user;
	}
	
	
	private UserResponseDto convertToUserRespnse (User user)
	{
		UserResponseDto ur= new UserResponseDto();
		
		ur.setUserName(user.getUserName());
		ur.setUserId(user.getUserId());
		ur.setUserEmail(user.getUserEmail());
		ur.setDateOfBirth(user.getDateOfBirth());
		return ur;
		
		
	}

	public ResponseEntity<ResponseStructure<String>> insertUser(UserRequestDto userReq) {
	
		User user = convertToUser(userReq, new User());
		ir.save(user);
		
		ResponseStructure<String> rs = new ResponseStructure<>();
		rs.setStatusCode(HttpStatus.CREATED.value());
		rs.setMessage(" User data saved successfully");
		rs.setData(" 1 USER ADDED  SUCCESSFULLY");
		
		return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.CREATED);	
	}
	
	

	

	
}
