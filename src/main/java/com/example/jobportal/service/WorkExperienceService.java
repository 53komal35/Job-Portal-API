package com.example.jobportal.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.jobportal.entity.Resume;
import com.example.jobportal.entity.WorkExperience;
import com.example.jobportal.exceptionhandling.EductationNotFoundException;
import com.example.jobportal.exceptionhandling.ResumeNotFoundException;
import com.example.jobportal.exceptionhandling.WorkExperienceFoundException;
import com.example.jobportal.repository.ResumeRepository;
import com.example.jobportal.repository.WorkExperienceRepository;
import com.example.jobportal.requestdto.WorkExperienceRequestDto;
import com.example.jobportal.responsedto.WorkExperienceResponseDto;
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
    
    private WorkExperienceResponseDto convertToWorkDto(WorkExperience work)
    {
    	WorkExperienceResponseDto dto = new WorkExperienceResponseDto();
    	dto.setDescription(work.getDescription());
    	dto.setEndDate(work.getEndDate());
    	dto.setJobRole(work.getJobRole());
    	dto.setJobStatus(work.getJobStatus());
    	dto.setOrganisation(work.getOrganisation());
    	dto.setStartDate(work.getStartDate());
    	dto.setYearsOfExperience(work.getYearsOfExperience());
    	dto.setExpId(work.getExpId());
    
    return dto;
   	
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


	public ResponseEntity<ResponseStructure<WorkExperienceResponseDto>> findWorkByWorkId(int workId) throws WorkExperienceFoundException {
		Optional<WorkExperience> optWork = workRepo.findById(workId);
		
		if (optWork.isPresent()) {
			
			 WorkExperience workOld = optWork.get();

			        WorkExperienceResponseDto dto = convertToWorkDto(workOld);
			        HashMap<String,String> hashMap = new HashMap<>();
			        
			        hashMap.put("Employee","/resumes/"+workOld.getAssociatedResume().getResumeId());
			        dto.setOptions(hashMap);
			          
			
			
			        
			ResponseStructure<WorkExperienceResponseDto> respStruc = new ResponseStructure<>();
			respStruc.setStatusCode(HttpStatus.FOUND.value());
			respStruc.setMessage(" work date fetched successfully");
			respStruc.setData(dto);
 
			return new ResponseEntity<ResponseStructure<WorkExperienceResponseDto>>(respStruc, HttpStatus.FOUND);
		}

		else throw new WorkExperienceFoundException(" workxperince  with given Id not Present");

	}

	public ResponseEntity<ResponseStructure<List<WorkExperienceResponseDto>>> findWorkByResumeId(int resumeId) throws ResumeNotFoundException, WorkExperienceFoundException {
		                     Optional<Resume> optResume = resumRepo.findById(resumeId);
		if (optResume.isPresent()) {
			
                          Resume resume = optResume.get();
                          
                          List<WorkExperience> workList = resume.getWorkList();
                          if(!workList.isEmpty()) {
                          List<WorkExperienceResponseDto> responseList = new ArrayList<>();
                          for(WorkExperience wk: workList)
                          {
                        	  
                        	  WorkExperienceResponseDto dto = convertToWorkDto(wk);
                        	  HashMap<String,String> hashMap = new HashMap<>();
          			        
          			        hashMap.put("Developer","/resumes/"+wk.getAssociatedResume().getResumeId());
                        	  dto.setOptions(hashMap);
                        	  responseList.add(dto);
                        	  
                          }
                   
			ResponseStructure<List<WorkExperienceResponseDto>> respStruc = new ResponseStructure<>();
			respStruc.setStatusCode(HttpStatus.FOUND.value());
			respStruc.setMessage(" work date fetched successfully");
			respStruc.setData(responseList);
 
			return new ResponseEntity<ResponseStructure<List<WorkExperienceResponseDto>>>(respStruc, HttpStatus.FOUND);
                          }
                          
                          
                          else throw new WorkExperienceFoundException("Works for this resume not present");
		}

		else throw new ResumeNotFoundException(" resume  with given Id not Present");

	}

	public ResponseEntity<ResponseStructure<String>> deleteWorkByResumeId(int resumeId, int workId) throws EductationNotFoundException, WorkExperienceFoundException, ResumeNotFoundException {
	       
			 Optional<Resume> optResume = resumRepo.findById(resumeId);
			
				if (optResume.isPresent()) {
					
					             Resume resume = optResume.get();
					             
					             List<WorkExperience> workList = resume.getWorkList();
					             
					             if(!workList.isEmpty()) {
					            	 
					            	  boolean flag =false;
					            	  
					            	 for(WorkExperience wk:workList)
					            	 {
					            		 
					            		 if(wk.getExpId()==workId)
					            		 { workList.remove(wk);
					            			 workRepo.deleteById(workId); 
					            			
					            			 flag=true;
					            			 break;
					            			 
					            		 }
					            		 
					            		 
					            	 }
					            	 if(flag==false) throw new EductationNotFoundException(" work  with this Id not found ");
					            	 
					            	 
					  
					ResponseStructure<String> respStruc = new ResponseStructure<>();
					respStruc.setStatusCode(HttpStatus.CREATED.value());
					respStruc.setMessage(" work data deleted successfully");
					respStruc.setData(" WORKEXPERIENCE DELETED SUCCESSFULLY");
		 
					return new ResponseEntity<ResponseStructure<String>>(respStruc, HttpStatus.CREATED);
					             } 
					             
					             else throw new WorkExperienceFoundException("Works in this resume not found");
				}

				else throw new ResumeNotFoundException(" resume  with given Id not Present");

	}
    
    
    
    
    
    
    

  

}
