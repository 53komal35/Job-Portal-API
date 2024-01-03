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

import com.example.jobportal.exceptionhandling.CompanyNotFoundException;
import com.example.jobportal.exceptionhandling.JobNotFoundException;
import com.example.jobportal.exceptionhandling.SkillNotFoundException;
import com.example.jobportal.requestdto.JobRequestDto;
import com.example.jobportal.responsedto.JobResponseDto;
import com.example.jobportal.service.JobService;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class JobController {
	
	@Autowired
	JobService jobService;
	
	
	
	@PostMapping("/companies/{compId}/jobs")  
	public ResponseEntity<ResponseStructure<String>> inserJob(@RequestBody @Valid JobRequestDto jobReq,@PathVariable int compId) 
			throws CompanyNotFoundException
	{
		
		 return jobService.insertJOb(jobReq,compId);
		
	}
	
	
	@GetMapping("/designations/{designation}/jobs")  
	public ResponseEntity<ResponseStructure<List<JobResponseDto>>> findJobByDesignation(@PathVariable String designation) throws JobNotFoundException 
			
	{
		
		 return jobService.findJOb(designation);
		
	}
	
	@GetMapping("/skills/{skill}/jobs")  
	public ResponseEntity<ResponseStructure<List<JobResponseDto>>> findJobBySkillName(@PathVariable String skill) throws JobNotFoundException, SkillNotFoundException 
			
	{
		
		 return jobService.findJObBySkill(skill);
		
	}
	
	
	@GetMapping("/locations/{loc}/jobs")  
	public ResponseEntity<ResponseStructure<List<JobResponseDto>>> findJobByLocation(@PathVariable String loc) throws JobNotFoundException 
			
	{
		
		 return jobService.findJObLocation(loc);
		
	}
	

	@PutMapping("/jobs/{jobId}")  
	public ResponseEntity<ResponseStructure<String>> updateJobById(@RequestBody  JobRequestDto jobReq,@PathVariable int jobId) throws JobNotFoundException
	{
		
		 return jobService.updateJobById(jobReq,jobId);
		
	}
	

	@DeleteMapping("/jobs/{jobId}")  
	public ResponseEntity<ResponseStructure<String>> deleteJob(@PathVariable int jobId) 
			throws JobNotFoundException
	{
		
		 return jobService.deleteJOb(jobId);
		
	}
	
	
	
//	skills 
//	List of skills 
}


