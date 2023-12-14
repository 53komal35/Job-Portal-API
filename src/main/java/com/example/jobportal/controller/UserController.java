package com.example.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobportal.requestdto.UserRequestDto;
import com.example.jobportal.service.UserService;
import com.example.jobportal.utility.ResponseStructure;






@RestController
public class UserController {
	
	@Autowired
	UserService is;
	
	
	
	@PostMapping("/users")  
	public ResponseEntity<ResponseStructure<String>> inserUser(@RequestBody  UserRequestDto userReq)
	{
		
		 return is.insertUser(userReq);
		
	}
	

	
	
}
