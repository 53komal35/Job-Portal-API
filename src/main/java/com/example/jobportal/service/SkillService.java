package com.example.jobportal.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.jobportal.entity.Job;
import com.example.jobportal.entity.Resume;
import com.example.jobportal.entity.Skill;
import com.example.jobportal.exceptionhandling.JobNotFoundException;
import com.example.jobportal.exceptionhandling.ResumeNotFoundException;
import com.example.jobportal.exceptionhandling.SkillNotFoundException;
import com.example.jobportal.repository.JobRepository;
import com.example.jobportal.repository.ResumeRepository;
import com.example.jobportal.repository.SkillRepository;
import com.example.jobportal.requestdto.SkillRequestDto;
import com.example.jobportal.responsedto.SkillResponseDto;
import com.example.jobportal.utility.ResponseStructure;

@Service
public class SkillService {
	
	@Autowired
	private SkillRepository skillRepo;
    @Autowired
	private ResumeRepository resumRepo;
    @Autowired
    private JobRepository jobRepo;

    private Skill CheckSkill(String skill )
    {
    	Skill oldSkill = skillRepo.findSkillByname(skill.toLowerCase());
    	if(oldSkill==null) 
    	{
    		Skill newSkill= new Skill();
    		newSkill.setSkillName(skill.toLowerCase());
    		skillRepo.save(newSkill);
    		return newSkill;
    	}
                 	
    	else return oldSkill;
    	
    }
    
    private List<Skill> convertToSkill(SkillRequestDto reqDto, List<Skill> skillList)
    {    
    	
    	String[] skillsArray = reqDto.getSkills();
    
    	for(String sk:skillsArray)
    	{
    		Skill Skill = CheckSkill(sk);
    		
    		if(!skillList.contains(Skill))
    		skillList.add(Skill);
    	}
    	return skillList ;
    	
    }
    
    private SkillResponseDto convertToSkillResponse(Skill skill)
    {
    	 SkillResponseDto dto = new SkillResponseDto();
    	 dto.setSkillId(skill.getSkillId());
    	 dto.setSkillName(skill.getSkillName());
    	 return dto;
    	
    }
    
    private List<Skill> removeSkill(Skill skill,List<Skill> skillList)
    {   
    	
    	 
    	 if(skill!=null)
    	skillList.remove(skill);
    	 
    	 return skillList;
    	 
    	 
    	
    }
    
    
    
	public ResponseEntity<ResponseStructure<String>> insertSkill(SkillRequestDto skillReq,int resumId) throws ResumeNotFoundException 
			 {
		
		   Optional<Resume> optResum = resumRepo.findById(resumId);
		
		   if(optResum.isPresent()) {
			   Resume resume = optResum.get();
		                
			         List<Skill> listSkills = convertToSkill(skillReq,new ArrayList<Skill>());
			         
	                   resume.setSkillMap(listSkills);
	                   resumRepo.save(resume);
	                   
	                   
				ResponseStructure<String> respStruc = new ResponseStructure<>();
				respStruc.setStatusCode(HttpStatus.CREATED.value());
				respStruc.setMessage(" Skill data saved successfully");
				respStruc.setData("  SKILLLIST ADDED  SUCCESSFULLY");

				return new ResponseEntity<ResponseStructure<String>>(respStruc, HttpStatus.CREATED);
				
		}  else throw new ResumeNotFoundException("resume with given id not present");
		
		
	}
	
	
	public ResponseEntity<ResponseStructure<String>> insertSkillinJob(SkillRequestDto skillReq,int jobId) throws JobNotFoundException 
	 {

                Optional<Job> optJob = jobRepo.findById(jobId);
                

  if(optJob.isPresent()) {
	  
	   Job job = optJob.get();
               
	         List<Skill> skillList = job.getSkillList();
	         List<Skill> newskillList;
	         if(!skillList.isEmpty())
	        	 
	         {  
	        	 newskillList = convertToSkill(skillReq, skillList);
	         
	         } else {
				newskillList = convertToSkill(skillReq,new ArrayList<Skill>());
			}
              
	         job.setSkillList(newskillList);
	         jobRepo.save(job);
	         
              
		ResponseStructure<String> respStruc = new ResponseStructure<>();
		respStruc.setStatusCode(HttpStatus.CREATED.value());
		respStruc.setMessage(" Skill data saved successfully");
		respStruc.setData("  SKILL LIST ADDED TO JOB SUCCESSFULLY");

		return new ResponseEntity<ResponseStructure<String>>(respStruc, HttpStatus.CREATED);
		
}  else throw new JobNotFoundException("job with given id not present");


}
	
	public ResponseEntity<ResponseStructure<String>> updateSkill(SkillRequestDto skillReq,int resumId) throws ResumeNotFoundException 
	 {

  Optional<Resume> optResum = resumRepo.findById(resumId);

  if(optResum.isPresent()) {
	   Resume resume = optResum.get();
               
	          List<Skill> updSkillList = convertToSkill(skillReq,resume.getSkillMap());
	          
	         
              resume.setSkillMap(updSkillList);
              resumRepo.save(resume);
              
              
		ResponseStructure<String> respStruc = new ResponseStructure<>();
		respStruc.setStatusCode(HttpStatus.CREATED.value());
		respStruc.setMessage(" Skill data updated successfully");
		respStruc.setData("  SKILL LIST  UPDATED SUCCESSFULLY");

		return new ResponseEntity<ResponseStructure<String>>(respStruc, HttpStatus.CREATED);
		
}  else throw new ResumeNotFoundException("resume with given id not present");


}
	
	
	public ResponseEntity<ResponseStructure<String>> updateSkillInJobList(SkillRequestDto skillReq,int jobId) throws JobNotFoundException 
	 {

 Optional<Job> optJob = jobRepo.findById(jobId);

 if(optJob.isPresent()) {
	   Job job = optJob.get();
              
	          List<Skill> updSkillList = convertToSkill(skillReq,job.getSkillList());
	          
	         
             job.setSkillList(updSkillList);
             jobRepo.save(job);
             
             
		ResponseStructure<String> respStruc = new ResponseStructure<>();
		respStruc.setStatusCode(HttpStatus.ACCEPTED.value());
		respStruc.setMessage(" Skill data updated successfully");
		respStruc.setData("  SKILL LIST  UPDATED IN JOB SUCCESSFULLY");

		return new ResponseEntity<ResponseStructure<String>>(respStruc, HttpStatus.ACCEPTED);
		
}  else throw new JobNotFoundException("job with given id not present");


}
	
	
	public ResponseEntity<ResponseStructure<String>> deleteSkillInResume(int resumeid,String skill) throws SkillNotFoundException, ResumeNotFoundException 
	 {
		
		 Optional<Resume> optRes = resumRepo.findById(resumeid);
		  if(optRes.isPresent()) {
			  Resume resume = optRes.get();
		               
                      Skill skilltoDel = skillRepo.findSkillByname(skill.toLowerCase());
                      if (skilltoDel!=null) {
                    	  
                           
                            List<Skill> updatedList = removeSkill(skilltoDel,resume.getSkillMap());

                                             resume.setSkillMap(updatedList);
                                             resumRepo.save(resume);
	    
		ResponseStructure<String> respStruc = new ResponseStructure<>();
		respStruc.setStatusCode(HttpStatus.ACCEPTED.value());
		respStruc.setMessage(" Skill data removed  successfully");
		respStruc.setData("  SKILL DELETED FROM LIST SUCCESSFULLY");

		return new ResponseEntity<ResponseStructure<String>>(respStruc, HttpStatus.ACCEPTED);
		
}  else  throw new SkillNotFoundException("skill not present in resume");
	
 
		  } else throw new ResumeNotFoundException("resume not present with this Id");

}

	
	public ResponseEntity<ResponseStructure<String>> deleteSkillInJob(int jobId,String skill) throws SkillNotFoundException, JobNotFoundException 
	 {
		
	                Optional<Job> optJob = jobRepo.findById(jobId);
		  if(optJob.isPresent()) {
			            Job job = optJob.get();
		               
                     Skill skilltoDel = skillRepo.findSkillByname(skill.toLowerCase());
                     if (skilltoDel!=null) {
                   	  
                          
                           List<Skill> updatedList = removeSkill(skilltoDel,job.getSkillList());

                                            job.setSkillList(updatedList);
                                            jobRepo.save(job);
	    
		ResponseStructure<String> respStruc = new ResponseStructure<>();
		respStruc.setStatusCode(HttpStatus.ACCEPTED.value());
		respStruc.setMessage(" Skill data removed  successfully");
		respStruc.setData("  SKILL DELETED FROM JOBLIST SUCCESSFULLY");

		return new ResponseEntity<ResponseStructure<String>>(respStruc, HttpStatus.ACCEPTED);
		
}  else  throw new SkillNotFoundException("skill not present in resume");
	

		  } else throw new JobNotFoundException("job not present with this Id");

}
	public ResponseEntity<ResponseStructure<SkillResponseDto>> findSkillByName(String skillName) throws SkillNotFoundException {
		    Skill skill = skillRepo.findSkillByname(skillName.toLowerCase());  // dont forget to convertt to lower case
		    
		  if(skill!=null) {
			  
			   HashMap<String,String> hashMap = new HashMap<>();
			   
		  SkillResponseDto dto = convertToSkillResponse(skill);
		  
		  hashMap.put("Requirement", "requirement to add");
			  dto.setOptions(hashMap);
		ResponseStructure<SkillResponseDto> respStruc = new ResponseStructure<>();
		respStruc.setStatusCode(HttpStatus.FOUND.value());
		respStruc.setMessage(" Skill data fetched  successfully");
		respStruc.setData(dto);

		return new ResponseEntity<ResponseStructure<SkillResponseDto>>(respStruc, HttpStatus.FOUND);
		
}  else  throw new SkillNotFoundException("skill not present in dataBase");
	
	}

	public ResponseEntity<ResponseStructure<List<SkillResponseDto>>> findSkillByResumeId(int resumeId) throws SkillNotFoundException {
	  
		Optional<Resume> optResume = resumRepo.findById(resumeId);
	if(optResume.isPresent())	
	   {     Resume resume = optResume.get();
		    List<Skill> listSkill = resume.getSkillMap();
		    List<SkillResponseDto> respList = new ArrayList<>();
		    
		    for(Skill sk:listSkill) {
		   HashMap<String,String> hashMap = new HashMap<>();
		   
	  SkillResponseDto dto = convertToSkillResponse(sk);
	  
	  hashMap.put("Requirement", "requirement to add");
		  dto.setOptions(hashMap);
		  respList.add(dto);
		    }
	ResponseStructure<List<SkillResponseDto>> respStruc = new ResponseStructure<>();
	respStruc.setStatusCode(HttpStatus.FOUND.value());
	respStruc.setMessage(" Skill data fetched  successfully");
	respStruc.setData(respList);

	return new ResponseEntity<ResponseStructure<List<SkillResponseDto>>>(respStruc, HttpStatus.FOUND);
	
}  else  throw new SkillNotFoundException("skill not present in dataBase");

	}

	public ResponseEntity<ResponseStructure<List<SkillResponseDto>>> findSkillByJobId(int jobId) throws SkillNotFoundException, JobNotFoundException {
		  
		 Optional<Job> optJob = jobRepo.findById(jobId);
	if(optJob.isPresent())	
	   {       Job job = optJob.get();
		    List<Skill> listSkill = job.getSkillList();
		    
		    if(!listSkill.isEmpty()) {
		    List<SkillResponseDto> respList = new ArrayList<>();
		    
		    for(Skill sk:listSkill) {
		   HashMap<String,String> hashMap = new HashMap<>();
		   
	  SkillResponseDto dto = convertToSkillResponse(sk);
	  
	  hashMap.put("Requirement", "requirement");
		  dto.setOptions(hashMap);
		  respList.add(dto);
		    }
	ResponseStructure<List<SkillResponseDto>> respStruc = new ResponseStructure<>();
	respStruc.setStatusCode(HttpStatus.FOUND.value());
	respStruc.setMessage(" Skill data fetched  successfully");
	respStruc.setData(respList);

	return new ResponseEntity<ResponseStructure<List<SkillResponseDto>>>(respStruc, HttpStatus.FOUND);
		    } else throw new SkillNotFoundException("Skills in JOb list nor present");
	
}  else  throw new JobNotFoundException("job not present in dataBase");

	}


	
	
}
