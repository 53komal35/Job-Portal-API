package com.example.jobportal.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.jobportal.entity.Project;
import com.example.jobportal.entity.Resume;
import com.example.jobportal.exceptionhandling.ProjectNotFoundException;
import com.example.jobportal.exceptionhandling.ResumeNotFoundException;
import com.example.jobportal.repository.ProjectRepository;
import com.example.jobportal.repository.ResumeRepository;
import com.example.jobportal.requestdto.ProjectRequestDto;
import com.example.jobportal.responsedto.ProjectResponseDto;
import com.example.jobportal.utility.ResponseStructure;

@Service
public class ProjectService {
	@Autowired
	private ProjectRepository proRepo;
    @Autowired
	private ResumeRepository resumRepo;


   private Project convertToProject(ProjectRequestDto proReq,Project proj)
   {
	   proj.setDescription(proReq.getDescription());
	   proj.setProjectName(proReq.getProjectName());
	   proj.setSourceCode(proReq.getSourceCode());
	   proj.setTechStack(proReq.getTechStack());
	   proj.setWebsite(proReq.getWebsite());
	   
	   return proj;
   }
   
   private ProjectResponseDto convertToProjectResponse (Project pro)
   {    ProjectResponseDto dto = new ProjectResponseDto();
	   dto.setDescription(pro.getDescription());
	   dto.setProId(pro.getProId());
	   dto.setProjectName(pro.getProjectName());
	   dto.setSourceCode(pro.getSourceCode());
	   dto.setTechStack(pro.getTechStack());
	   dto.setWebsite(pro.getWebsite());
	   return dto;
 
   }

    
    
    
	public ResponseEntity<ResponseStructure<String>> insertProject(ProjectRequestDto proReq,int resumId) throws ResumeNotFoundException 
			 {
		
		   Optional<Resume> optResum = resumRepo.findById(resumId);
		
		   if(optResum.isPresent()) {
			   Resume resume = optResum.get();
		                
			          Project project = convertToProject(proReq, new Project());
			          project.setResumeMap(resume);
			          proRepo.save(project);
	                   
			         // resumRepo.save(resume);
				ResponseStructure<String> respStruc = new ResponseStructure<>();
				respStruc.setStatusCode(HttpStatus.CREATED.value());
				respStruc.setMessage(" Project data saved successfully");
				respStruc.setData("  PROJECT  ADDED  SUCCESSFULLY");

				return new ResponseEntity<ResponseStructure<String>>(respStruc, HttpStatus.CREATED);
				
		}  else throw new ResumeNotFoundException("resume with given id not present");
		
		
	}

	public ResponseEntity<ResponseStructure<String>> updateProject(ProjectRequestDto proReq, int projectId) throws ProjectNotFoundException {
		
		Optional<Project> optpro = proRepo.findById(projectId);
		 
		   if(optpro.isPresent()) {
			          
			   Project oldProject= optpro.get();
		                
			         Project newProject = convertToProject(proReq, oldProject);
			         
	                   proRepo.save(newProject);
			        
				ResponseStructure<String> respStruc = new ResponseStructure<>();
				respStruc.setStatusCode(HttpStatus.CREATED.value());
				respStruc.setMessage(" Project data updated successfully");
				respStruc.setData("  PROJECT  UPDATED SUCCESSFULLY");

				return new ResponseEntity<ResponseStructure<String>>(respStruc, HttpStatus.CREATED);
				
		}  else throw new ProjectNotFoundException(" project  with given id not present");
		
	}


	
	


}
