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

import com.example.jobportal.exceptionhandling.EductationNotFoundException;
import com.example.jobportal.exceptionhandling.ResumeNotFoundException;
import com.example.jobportal.exceptionhandling.WorkExperienceFoundException;
import com.example.jobportal.requestdto.WorkExperienceRequestDto;
import com.example.jobportal.responsedto.WorkExperienceResponseDto;
import com.example.jobportal.service.WorkExperienceService;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class WorkExperienceController {
	
	@Autowired
	WorkExperienceService workExperienceService;
	

	@PostMapping("/resumes/{resumId}/works")  
	public ResponseEntity<ResponseStructure<String>> insertWork(@PathVariable int resumId,@RequestBody @Valid WorkExperienceRequestDto reqWork) throws ResumeNotFoundException 
		 
	{
		
		 return workExperienceService.insertWork(reqWork,resumId);
		
	}

	@PutMapping("/works/{workId}")  
	public ResponseEntity<ResponseStructure<String>> updateWork(@PathVariable int workId ,@RequestBody @Valid WorkExperienceRequestDto reqWork) throws WorkExperienceFoundException 
		 
	{
		
		 return workExperienceService.updateWork(reqWork, workId);
		
	}
	
	@GetMapping("/works/{workId}")  
	public ResponseEntity<ResponseStructure<WorkExperienceResponseDto>> findWorkByWorkId(@PathVariable int workId ) throws WorkExperienceFoundException 
		 
	{
		
		 return workExperienceService.findWorkByWorkId(workId);
		
	}
	
	@GetMapping("/resumes/{resumeId}/works")  
	public ResponseEntity<ResponseStructure<List<WorkExperienceResponseDto>>> findWorkByResumeId(@PathVariable int resumeId ) throws WorkExperienceFoundException, ResumeNotFoundException 
		 
	{
		
		 return workExperienceService.findWorkByResumeId(resumeId);
		
	}
	
	@DeleteMapping("/resumes/{resumeId}/works/{workId}")  
	public ResponseEntity<ResponseStructure<String>> deleteWorkByResumeId(@PathVariable int resumeId ,@PathVariable int workId ) throws WorkExperienceFoundException, EductationNotFoundException, ResumeNotFoundException 
		 
	{
		
		 return workExperienceService.deleteWorkByResumeId(resumeId, workId);
		
	}

}
