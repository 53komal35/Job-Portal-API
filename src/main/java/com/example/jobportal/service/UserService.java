package com.example.jobportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.jobportal.entity.User;
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
	
		user.setUsername(userRq.getUsername());
		user.setEmail(userRq.getEmail());
		user.setPassword(userRq.getPassword());
	
		return user;
	}
	
	
	private UserResponseDto convertToUserRespnse (User user)
	{
		 UserResponseDto responseDto = new UserResponseDto();
		  responseDto.setEmail(user.getEmail());
		  responseDto.setUserId(user.getUserId());
		  responseDto.setUserrole(user.getUserRole());
		  responseDto.setUsername(user.getUsername());
		 

		 return responseDto;
		
		
	}

	public ResponseEntity<ResponseStructure<String>> insertUser(UserRequestDto userReq) {
	
		User user = convertToUser(userReq, new User());
		userRepo.save(user);
		ResponseStructure<String> rs = new ResponseStructure<>();
		rs.setStatusCode(HttpStatus.CREATED.value());
		rs.setMessage(" User data saved successfully");
		rs.setData(" 1 USER ADDED  SUCCESSFULLY");
		
		return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.CREATED);	
	}
	
	

	

	
}
