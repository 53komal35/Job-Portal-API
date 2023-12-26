package com.example.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobportal.exceptionhandling.IllegalAccssException;
import com.example.jobportal.exceptionhandling.ResumeNotFoundException;
import com.example.jobportal.exceptionhandling.UserNotFoundException;
import com.example.jobportal.requestdto.ResumeRequestDto;
import com.example.jobportal.responsedto.ResumeResponseDto;
import com.example.jobportal.service.ResumeService;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class ResumeController {
	
	@Autowired
	ResumeService resumeService;
	
	
	
	@PostMapping("/users/{userId}/resumes")  
	public ResponseEntity<ResponseStructure<String>> inserResume(@RequestBody @Valid ResumeRequestDto resumeReq,@PathVariable int userId
			) throws IllegalAccssException, UserNotFoundException
	{
		
		 return resumeService.insertResume(resumeReq,userId);
		
	}
	
	
	@GetMapping("/resumes/{resumeId}")  
	public ResponseEntity<ResponseStructure<ResumeResponseDto>> findResumeById(@PathVariable int resumeId
			) throws ResumeNotFoundException
	{
		
		 return resumeService.findResumeById(resumeId);
		
	}
	
	@PutMapping("/resumes/{resumeId}")  
	public ResponseEntity<ResponseStructure<String>> updateResumeById(@RequestBody @Valid ResumeRequestDto reqResume ,@PathVariable int resumeId
			) throws ResumeNotFoundException
	{
		
		 return resumeService.UpdateResume(reqResume, resumeId);
		
	}
	
}
