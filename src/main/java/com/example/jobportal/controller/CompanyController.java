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

import com.example.jobportal.enums.BusinessType;
import com.example.jobportal.exceptionhandling.CompanyNotFoundException;
import com.example.jobportal.exceptionhandling.IllegalAccssException;
import com.example.jobportal.exceptionhandling.UserNotFoundException;
import com.example.jobportal.requestdto.CompanyRequestDto;
import com.example.jobportal.responsedto.CompanyResponseDto;
import com.example.jobportal.service.CompanyService;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class CompanyController {
	
	@Autowired
	CompanyService compService;
	
	
	
	@PostMapping("/users/{userId}/BusinessType/{buss}/companies")  
	public ResponseEntity<ResponseStructure<String>> inserCompany(@RequestBody @Valid CompanyRequestDto compReq,@PathVariable int userId,
			@PathVariable BusinessType buss) throws IllegalAccssException, UserNotFoundException
	{
		
		 return compService.insertCompany(compReq,buss,userId);
		
	}
	
	@GetMapping("/companies/{compId}")  
	public ResponseEntity<ResponseStructure<CompanyResponseDto>> findCompById(@PathVariable int  compId) throws CompanyNotFoundException
	{
		
		 return compService.findCompById(compId);
		
	}
	
	@PutMapping("/companies/{compId}")  
	public ResponseEntity<ResponseStructure<String>> updateUser(@RequestBody CompanyRequestDto compReq,@PathVariable int compId) throws CompanyNotFoundException 
	{
		
		 return compService.updateCompany(compReq,compId);
		
	}	
	
	
	@DeleteMapping("/companies/{compId}")  
	public ResponseEntity<ResponseStructure<String>> deleteCompById(@PathVariable int  compId) throws CompanyNotFoundException
	{
		
		 return compService.deleteCompById(compId);
		
	}
	
}
