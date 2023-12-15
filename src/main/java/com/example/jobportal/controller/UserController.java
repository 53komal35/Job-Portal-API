package com.example.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobportal.enums.UserRole;
import com.example.jobportal.exceptionhandling.UserNotFoundException;
import com.example.jobportal.requestdto.UserRequestDto;
import com.example.jobportal.responsedto.UserResponseDto;
import com.example.jobportal.service.UserService;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;






@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	
	
	@PostMapping("/userroles/{role}/users")  
	public ResponseEntity<ResponseStructure<String>> inserUser(@RequestBody @Valid UserRequestDto userReq,@PathVariable UserRole role)
	{
		
		 return userService.insertUser(userReq,role);
		
	}
	
	
	@GetMapping("/users/{userId}")  
	public ResponseEntity<ResponseStructure<UserResponseDto>> findUserById(@PathVariable int  userId) throws UserNotFoundException
	{
		
		 return userService.findUserById(userId);
		
	}
	
	@PutMapping("/users/{userId}")  
	public ResponseEntity<ResponseStructure<String>> updateUserById(@RequestBody @Valid UserRequestDto userReq,@PathVariable int userId) throws UserNotFoundException
	{
		
		 return userService.updateUserById(userReq,userId);
		
	}
	
	@DeleteMapping("/users/{userId}")  
	public ResponseEntity<ResponseStructure<String>> DeleteUserById(@PathVariable int  userId) throws UserNotFoundException
	{
		
		 return userService.deleteUserById(userId);
		
	}

	
	
}
