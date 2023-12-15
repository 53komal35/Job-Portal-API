package com.example.jobportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.jobportal.entity.User;
import com.example.jobportal.repository.UserRepository;
import com.example.jobportal.requestdto.UserRequestDto;
import com.example.jobportal.responsedto.UserResponseDto;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;
	
	private User convertToUser(UserRequestDto userRq ,User user)
	{
	      user.setPassword(userRq.getPassword());
	      user.setEmail((userRq.getEmail()));
	      user.setUsername(userRq.getUsername());
	
	      user.setUserRole((userRq.getUserrole()));
	
		return user;
	}
	
	
	private UserResponseDto convertToUserRespnse (User user)
	{
		UserResponseDto userDto= new UserResponseDto();
		userDto.setEmail(user.getEmail()); 
		userDto.setUserId(user.getUserId());
		userDto.setUsername(user.getUsername());
		userDto.setUserrole(user.getUserRole());
		
		return userDto;
		
		
	}



	

	
}
