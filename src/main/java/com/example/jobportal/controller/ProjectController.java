package com.example.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobportal.exceptionhandling.IllegalAccssException;
import com.example.jobportal.exceptionhandling.ProjectNotFoundException;
import com.example.jobportal.exceptionhandling.ResumeNotFoundException;
import com.example.jobportal.exceptionhandling.UserNotFoundException;
import com.example.jobportal.requestdto.ProjectRequestDto;
import com.example.jobportal.service.ProjectService;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class ProjectController {
	
	@Autowired
	ProjectService projectService;
	
	
	
	@PostMapping("resumes/{resumeId}/projects")  
	public ResponseEntity<ResponseStructure<String>> inserProject(@RequestBody @Valid ProjectRequestDto proReq,@PathVariable int resumeId
			) throws ResumeNotFoundException
	{
		
		 return projectService.insertProject(proReq,resumeId);
		
	}
	
	@PutMapping("/projects/{projectId}")  
	public ResponseEntity<ResponseStructure<String>> updateProject(@RequestBody ProjectRequestDto proReq,@PathVariable int projectId
			) throws IllegalAccssException, UserNotFoundException, ResumeNotFoundException, ProjectNotFoundException
	{
		
		 return projectService.updateProject(proReq,projectId);
		
	}
  
	
}
