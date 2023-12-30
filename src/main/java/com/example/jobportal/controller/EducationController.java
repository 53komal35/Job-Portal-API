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

import com.example.jobportal.enums.EducationType;
import com.example.jobportal.exceptionhandling.EductationNotFoundException;
import com.example.jobportal.exceptionhandling.IllegalAccssException;
import com.example.jobportal.exceptionhandling.ResumeNotFoundException;
import com.example.jobportal.requestdto.EducationRequestDto;
import com.example.jobportal.responsedto.EducationResponseDto;
import com.example.jobportal.service.EducationService;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class EducationController {
	
	@Autowired
	EducationService educationService;
	
	
	
	@PostMapping("/resumes/{resumeId}/educationTypes/{eduType}/educations")  
	public ResponseEntity<ResponseStructure<String>> inserEducation(@RequestBody @Valid EducationRequestDto eduReq,@PathVariable int resumeId ,@PathVariable EducationType eduType
			) throws ResumeNotFoundException, IllegalAccssException 
	{
		
		 return educationService.insertEducation(eduReq,resumeId,eduType);
		
	}
	
	@PutMapping("/educations/{eduId}")  
	public ResponseEntity<ResponseStructure<String>> updateEducation(@RequestBody @Valid EducationRequestDto eduReq,@PathVariable int eduId 
			) throws EductationNotFoundException  
	{
		
		 return educationService.updateEducation(eduReq,eduId);
		
	}
	
	@GetMapping("/educations/{eduId}")  
	public ResponseEntity<ResponseStructure<EducationResponseDto>> findEducationById(@PathVariable int eduId 
			) throws EductationNotFoundException  
	{
		
		 return educationService.findEducationById(eduId);
	}		
	
	@GetMapping("/resumes/{resumeId}/educations")  
	public ResponseEntity<ResponseStructure<List<EducationResponseDto>>> findEducationByResumeId(@PathVariable int resumeId 
			) throws EductationNotFoundException, ResumeNotFoundException   
	{
		
		 return educationService.findEducationByResumeId(resumeId);
	}
	
	@DeleteMapping("/resumes/{resumeId}/educations/{eduId}")  
	public ResponseEntity<ResponseStructure<String>> deleteEducationByResumeId(@PathVariable int resumeId, 
			@PathVariable int eduId		) throws EductationNotFoundException, ResumeNotFoundException   
	{
		
		 return educationService.deleteEducationByResumeId(resumeId,eduId);
	}
  
	
}
