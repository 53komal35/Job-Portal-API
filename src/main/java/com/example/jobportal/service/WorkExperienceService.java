package com.example.jobportal.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.jobportal.entity.Company;
import com.example.jobportal.entity.Job;
import com.example.jobportal.entity.Resume;
import com.example.jobportal.entity.WorkExperience;
import com.example.jobportal.exceptionhandling.CompanyNotFoundException;
import com.example.jobportal.exceptionhandling.ResumeNotFoundException;
import com.example.jobportal.exceptionhandling.WorkExperienceFoundException;
import com.example.jobportal.repository.ResumeRepository;
import com.example.jobportal.repository.WorkExperienceRepository;
import com.example.jobportal.requestdto.WorkExperienceRequestDto;
import com.example.jobportal.utility.ResponseStructure;

import jakarta.validation.Valid;

@Service
public class WorkExperienceService {

    @Autowired
	private ResumeRepository resumRepo;
    @Autowired
    private WorkExperienceRepository workRepo;
    
    
    
    
    private WorkExperience convertToWork(WorkExperienceRequestDto dto, WorkExperience work)
    {
    	
    	work.setDescription(dto.getDescription());
    	work.setEndDate(dto.getEndDate());
    	work.setJobRole(dto.getJobRole());
    	work.setJobStatus(dto.getJobStatus());
    	work.setOrganisation(dto.getOrganisation());
    	work.setStartDate(dto.getStartDate());
    return work;
    	
  	
    }
    
    
	public ResponseEntity<ResponseStructure<String>> insertWork(WorkExperienceRequestDto reqWork, int resumId) throws ResumeNotFoundException {

          Optional<Resume> optResume = resumRepo.findById(resumId);		
		
		if (optResume.isPresent()) {
			
			Resume resume = optResume.get();

			          WorkExperience work = convertToWork(reqWork, new WorkExperience());
			          
			          work.setAssociatedResume(resume);
			         if(work.getJobStatus()==true) {work.setEndDate(null);}
			          
			          Period per;
			          if( work.getEndDate()!=null)
			          {
			        	  
			        	  per = Period.between(work.getStartDate(),  work.getEndDate());
			        	   work.setYearsOfExperience(per+"");
			        	       
			          }
			         
			          else {
			        	   per = Period.between(work.getStartDate(),LocalDate.now());
		        	       work.setYearsOfExperience(per+"");
		        	      
			          }
			
			          workRepo.save(work);
			ResponseStructure<String> respStruc = new ResponseStructure<>();
			respStruc.setStatusCode(HttpStatus.CREATED.value());
			respStruc.setMessage(" work data saved successfully");
			respStruc.setData(" WORKEXPERIENCE ADDED  SUCCESSFULLY");
 
			return new ResponseEntity<ResponseStructure<String>>(respStruc, HttpStatus.CREATED);
		}

		else throw new ResumeNotFoundException(" Resume with given Id not Present");


	}


	public ResponseEntity<ResponseStructure<String>> updateWork(@Valid WorkExperienceRequestDto reqWork, int workId) throws WorkExperienceFoundException {
              
		Optional<WorkExperience> optWork = workRepo.findById(workId);
		
			if (optWork.isPresent()) {
				
				 WorkExperience workOld = optWork.get();

				          WorkExperience workNew = convertToWork(reqWork, workOld);
				          
				          
				         if(workNew.getJobStatus()==true) {workNew.setEndDate(null);}
				          
				          Period per;
				          if( workNew.getEndDate()!=null)
				          {
				        	  
				        	  per = Period.between(workNew.getStartDate(),  workNew.getEndDate());
				        	   workNew.setYearsOfExperience(per+"");
				        	       
				          }
				         
				          else {
				        	   per = Period.between(workNew.getStartDate(),LocalDate.now());
			        	       workNew.setYearsOfExperience(per+"");
			        	      
				          }
				
				          workRepo.save(workNew);
				ResponseStructure<String> respStruc = new ResponseStructure<>();
				respStruc.setStatusCode(HttpStatus.CREATED.value());
				respStruc.setMessage(" work data updated successfully");
				respStruc.setData(" WORKEXPERIENCE UPDATED SUCCESSFULLY");
	 
				return new ResponseEntity<ResponseStructure<String>>(respStruc, HttpStatus.CREATED);
			}

			else throw new WorkExperienceFoundException(" workxperince  with given Id not Present");


	}
    
    
    
    
    
    
    

  

}
