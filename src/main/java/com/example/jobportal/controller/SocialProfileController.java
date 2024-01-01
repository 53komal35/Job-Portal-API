package com.example.jobportal.controller;

import java.util.List;

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
import com.example.jobportal.enums.SocialType;
import com.example.jobportal.exceptionhandling.CompanyNotFoundException;
import com.example.jobportal.exceptionhandling.IllegalAccssException;
import com.example.jobportal.exceptionhandling.ResumeNotFoundException;
import com.example.jobportal.exceptionhandling.SocialProfileNotFoundException;
import com.example.jobportal.exceptionhandling.UserNotFoundException;
import com.example.jobportal.requestdto.CompanyRequestDto;
import com.example.jobportal.requestdto.SocialProfileRequestDto;
import com.example.jobportal.responsedto.CompanyResponseDto;
import com.example.jobportal.responsedto.SocialProfileResponseDto;
import com.example.jobportal.service.CompanyService;
import com.example.jobportal.service.SocialProfileService;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class SocialProfileController {
	
	@Autowired
	SocialProfileService socialService;
	
	
	
	@PostMapping("/resumes/{resumeId}/socialType/{socialType}/socialProfiles")  
	public ResponseEntity<ResponseStructure<String>> insertSocialProfile(@RequestBody @Valid SocialProfileRequestDto 
			socialReq,@PathVariable int resumeId,
			@PathVariable SocialType socialType) throws IllegalAccssException, ResumeNotFoundException
	{
		
		 return socialService.insertSocialProfile(socialReq,socialType,resumeId);
		
	}
	
	@PutMapping("/resumes/{resumeId}/socialProfiles/{socialId}")  
	public ResponseEntity<ResponseStructure<String>> updatedSocialProfile(@RequestBody @Valid SocialProfileRequestDto 
			socialReq,@PathVariable int resumeId,
			@PathVariable int socialId) throws ResumeNotFoundException, SocialProfileNotFoundException 
	{
		
		 return socialService.updateSocialProfile(socialReq,resumeId,socialId);
		
	}
	
	
	@GetMapping("/socialProfiles/{socialId}")  
	public ResponseEntity<ResponseStructure<SocialProfileResponseDto>> findSocialProfileById(@PathVariable int socialId) throws SocialProfileNotFoundException 
	{
		
		 return socialService.findSocialProfileById(socialId);
		
	}
	
	@GetMapping("/resumes/{resumeId}/socialProfiles")  
	public ResponseEntity<ResponseStructure<List<SocialProfileResponseDto>>> findSocialProfileByResumeId(@PathVariable int resumeId) throws SocialProfileNotFoundException, ResumeNotFoundException 
	{
		
		 return socialService.findSocialProfileByResumeId(resumeId);
		
	}
	
}
