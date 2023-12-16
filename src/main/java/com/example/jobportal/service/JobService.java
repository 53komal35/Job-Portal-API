package com.example.jobportal.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.jobportal.entity.Company;
import com.example.jobportal.entity.Job;
import com.example.jobportal.entity.User;
import com.example.jobportal.enums.UserRole;
import com.example.jobportal.exceptionhandling.CompanyNotFoundException;
import com.example.jobportal.exceptionhandling.IllegalAccssException;
import com.example.jobportal.repository.CompanyRepository;
import com.example.jobportal.repository.JobRepository;
import com.example.jobportal.requestdto.CompanyRequestDto;
import com.example.jobportal.requestdto.JobRequestDto;
import com.example.jobportal.responsedto.CompanyResponseDto;
import com.example.jobportal.responsedto.JobResponseDto;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;

@Service
public class JobService {
	
	@Autowired
	JobRepository jobRepo;
   
	@Autowired
	CompanyRepository compRepo;

	private Job convertToJob(JobRequestDto jobRq, Job job) {
		
		job.setCtc(jobRq.getCtc());
		job.setDesignation(jobRq.getDesignation());
		job.setJobRole(jobRq.getJobRole());
		job.setLocation(jobRq.getLocation());
		
		return job;
	
	}

	private JobResponseDto convertToJobResponse(Job job) {
		
		 
		 JobResponseDto dto = new JobResponseDto() ;
		dto.setCtc(job.getCtc());
		dto.setDesignation(job.getDesignation());
		dto.setJobId(job.getJobId());
		dto.setJobRole(job.getJobRole());
		dto.setLocation(job.getLocation());
	 return dto;

	}
	
	
	

	public ResponseEntity<ResponseStructure<String>> insertJOb(@Valid JobRequestDto jobReq, int compId) throws CompanyNotFoundException {
		
	                  Optional<Company> optComp = compRepo.findById(compId);

		if (optComp.isPresent()) {
			Company company = optComp.get();
			  
			      Job job = convertToJob(jobReq,new Job());
			      job.setCompMap(company);
			      
			      jobRepo.save(job);
		

				ResponseStructure<String> respStruc = new ResponseStructure<>();
				respStruc.setStatusCode(HttpStatus.CREATED.value());
				respStruc.setMessage(" Jobsaved successfully");
				respStruc.setData(" 1 JOB ADDED  SUCCESSFULLY");

				return new ResponseEntity<ResponseStructure<String>>(respStruc, HttpStatus.CREATED);
			}

			else throw new CompanyNotFoundException(" Company required to add Jobs");
			

	
			
	}
	
	

}
