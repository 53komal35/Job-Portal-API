package com.example.jobportal.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.jobportal.entity.User;
import com.example.jobportal.enums.UserRole;
import com.example.jobportal.exceptionhandling.UserNotFoundException;
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
	      user.setEmail(userRq.getEmail());
	      user.setPassword(userRq.getPassword());
	      user.setUsername(userRq.getUsername());
	     
		
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
		
		ResponseStructure<String> respStruc = new ResponseStructure<>();
		respStruc.setStatusCode(HttpStatus.CREATED.value());
		respStruc.setMessage(" User data saved successfully");
		respStruc.setData(" 1 USER ADDED  SUCCESSFULLY");
		
		return new ResponseEntity<ResponseStructure<String>>(respStruc, HttpStatus.CREATED);	
	}


	public ResponseEntity<ResponseStructure<UserResponseDto>> findUserById(int userId) throws UserNotFoundException {
		
		
		Optional<User> optUser = userRepo.findById(userId);
		if(optUser.isPresent())
		{
			UserResponseDto dto = convertToUserRespnse(optUser.get());
			
			ResponseStructure<UserResponseDto> responseStruct = new ResponseStructure<UserResponseDto>();
			responseStruct.setMessage("user found successfully");
			responseStruct.setStatusCode(HttpStatus.FOUND.value());
			responseStruct.setData(dto);
			
			return  new ResponseEntity<ResponseStructure<UserResponseDto>>(responseStruct,HttpStatus.FOUND);
			
			
		}
		
		else throw new UserNotFoundException("user with the given  Id not present");
	
	}
	
	

	

	
}
