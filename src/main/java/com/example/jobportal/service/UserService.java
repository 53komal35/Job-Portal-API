package com.example.jobportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.jobportal.entity.User;
import com.example.jobportal.enums.UserRole;
import com.example.jobportal.repository.UserRepository;
import com.example.jobportal.requestdto.UserRequestDto;
import com.example.jobportal.responsedto.UserResponseDto;
import com.example.jobportal.utility.ResponseStructure;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;
	
	private User convertToUser(UserRequestDto userRq ,User user)
	{
		userRq.setEmail(user.getEmail());
		userRq.setPassword(user.getPassword());
		userRq.setUsername(user.getUsername());
	
		
		return user;
	}
	
	
	private UserResponseDto convertToUserRespnse (User user)
	{
		UserResponseDto userResp= new UserResponseDto();
		userResp.setEmail(user.getEmail());
		userResp.setUserId(user.getUserId());
		userResp.setUsername(user.getUsername());
		userResp.setUserrole(user.getUserRole());
		 return userResp;
		
		
		
	}

	public ResponseEntity<ResponseStructure<String>> insertUser(UserRequestDto userReq, UserRole role) {
	
		User user = convertToUser(userReq, new User());
		user.setUserRole(role);
		userRepo.save(user);
		
		ResponseStructure<String> rs = new ResponseStructure<>();
		rs.setStatusCode(HttpStatus.CREATED.value());
		rs.setMessage(" User data saved successfully");
		rs.setData(" 1 USER ADDED  SUCCESSFULLY");
		
		return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.CREATED);	
	}
	
	

	

	
}
