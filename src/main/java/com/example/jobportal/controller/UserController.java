package com.example.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobportal.enums.UserRole;
import com.example.jobportal.requestdto.UserRequestDto;
import com.example.jobportal.service.UserService;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;






@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	
	
	@PostMapping("/userrole/{role}/users")  
	public ResponseEntity<ResponseStructure<String>> inserUser(@RequestBody @Valid UserRequestDto userReq,UserRole role)
	{
		
		 return userService.insertUser(userReq,role);
		
	}
	

	
	
}
