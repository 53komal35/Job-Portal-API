package com.example.jobportal.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.jobportal.entity.Resume;
import com.example.jobportal.entity.Skill;
import com.example.jobportal.entity.User;
import com.example.jobportal.enums.UserRole;
import com.example.jobportal.exceptionhandling.IllegalAccssException;
import com.example.jobportal.exceptionhandling.ResumeNotFoundException;
import com.example.jobportal.exceptionhandling.SkillNotFoundException;
import com.example.jobportal.exceptionhandling.UserNotFoundException;
import com.example.jobportal.repository.ResumeRepository;
import com.example.jobportal.repository.SkillRepository;
import com.example.jobportal.repository.UserRepository;
import com.example.jobportal.requestdto.ResumeRequestDto;
import com.example.jobportal.responsedto.ResumeResponseDto;
import com.example.jobportal.utility.ResponseStructure;

@Service
public class ResumeService {

	@Autowired
	private ResumeRepository resumeRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private SkillRepository skillRepo;

	private Resume convertToResume(ResumeRequestDto resumeRq, Resume resume) {

		resume.setObjective(resumeRq.getObjective());

		return resume;
	}

	private ResumeResponseDto convertToResumeRespnse(Resume resume) {
		ResumeResponseDto ResumeResp = new ResumeResponseDto();
		ResumeResp.setResumeId(resume.getResumeId());
		ResumeResp.setObjective(resume.getObjective());

		return ResumeResp;

	}

	public ResponseEntity<ResponseStructure<String>> insertResume(ResumeRequestDto ResumeReq, int userId)
			throws UserNotFoundException, IllegalAccssException {

		Optional<User> optUser = userRepo.findById(userId);

		if (optUser.isPresent()) {
			User user = optUser.get();
			if (user.getUserRole() == UserRole.APPLICANT) {
				Resume resume = convertToResume(ResumeReq, new Resume());
				resume.setUserMap(user); // since column is not added this mapping is not updated in User Table Column but now i updated
				//user.setResumeMap(resume);// now no need of this bcz i achanged the mapped by 
 System.out.println(" now third time");
				resumeRepo.save(resume);

				ResponseStructure<String> respStruc = new ResponseStructure<>();
				respStruc.setStatusCode(HttpStatus.CREATED.value());
				respStruc.setMessage(" Resume data saved successfully");
				respStruc.setData(" 1 Resume ADDED  SUCCESSFULLY");

				return new ResponseEntity<ResponseStructure<String>>(respStruc, HttpStatus.CREATED);
			}

			else
				throw new IllegalAccssException(" This user cant add resume");
		}

		else
			throw new UserNotFoundException("User not found with this Id");
	}

	public ResponseEntity<ResponseStructure<ResumeResponseDto>> findResumeById(int jobId)
			throws ResumeNotFoundException {

		Optional<Resume> optResum = resumeRepo.findById(jobId);

		if (optResum.isPresent()) {

			Resume resume = optResum.get();

			ResumeResponseDto dto = convertToResumeRespnse(resume);

			HashMap<String, String> hasmap = new HashMap<>();
			hasmap.put("user","/users/"+ resume.getUserMap().getUserId());
			hasmap.put("Projects","/resumes/"+resume.getResumeId()+"/projects");
			hasmap.put("Skills", "/resumes/"+resume.getResumeId()+"/skills");
			hasmap.put("Works","/resumes/"+resume.getResumeId()+"/works");
			dto.setOptions(hasmap);

			ResponseStructure<ResumeResponseDto> responseStruct = new ResponseStructure<ResumeResponseDto>();
			responseStruct.setMessage(" resume found successfully");
			responseStruct.setStatusCode(HttpStatus.FOUND.value());
			responseStruct.setData(dto);

			return new ResponseEntity<ResponseStructure<ResumeResponseDto>>(responseStruct, HttpStatus.FOUND);

		}

		else
			throw new ResumeNotFoundException(" resume with the given  Id not present");

	}
	
	
	public ResponseEntity<ResponseStructure<String>> UpdateResume(ResumeRequestDto ResumeReq, int resumeId)
			throws  ResumeNotFoundException {

	       Optional<Resume> optResume = resumeRepo.findById(resumeId);

		if (optResume.isPresent()) {
			
			
			
				Resume resume = convertToResume(ResumeReq,optResume.get() );
				
				resumeRepo.save(resume);

				ResponseStructure<String> respStruc = new ResponseStructure<>();
				respStruc.setStatusCode(HttpStatus.CREATED.value());
				respStruc.setMessage(" Resume data updated successfully");
				respStruc.setData(" 1 RESUME UPDATED  SUCCESSFULLY");

				return new ResponseEntity<ResponseStructure<String>>(respStruc, HttpStatus.CREATED);
			}

			else
				throw new ResumeNotFoundException(" resume not found with this Id");
		}

	public ResponseEntity<ResponseStructure<List<ResumeResponseDto>>> findResumeByskillName(String skillName) throws ResumeNotFoundException, SkillNotFoundException {
		   
		Skill skill = skillRepo.findSkillByname(skillName.toLowerCase());
	

		if (skill!=null) {
			                 
			
			          List<Resume> resumeList = resumeRepo.findAllBySkillMap(skill);
			           List<ResumeResponseDto> resumeRespList = new ArrayList<>();
           if(!resumeList.isEmpty()) {
		                    
        	   for(Resume res:resumeList)
        	   {
        		   ResumeResponseDto dto = convertToResumeRespnse(res);
        		    HashMap<String,String> hashMap = new HashMap<>();
        		    hashMap.put("Applicant", "/users/"+res.getUserMap().getUserId());
        		 //   hasmap.put("user","/users/"+ resume.getUserMap().getUserId());
        			hashMap.put("Projects","/resumes/"+res.getResumeId()+"/projects");
        			hashMap.put("Skills", "resumes/"+res.getResumeId()+"/skills");
        		     hashMap.put(skillName, skillName);
        		   dto.setOptions(hashMap);
        		   resumeRespList.add(dto);
        		   
        		   
        	   }
        	   


			ResponseStructure<List<ResumeResponseDto>> responseStruct = new ResponseStructure<>();
			responseStruct.setMessage(" resume found successfully");
			responseStruct.setStatusCode(HttpStatus.FOUND.value());
			responseStruct.setData(resumeRespList);

			return new ResponseEntity<ResponseStructure<List<ResumeResponseDto>>>(responseStruct, HttpStatus.FOUND);
           }
           
           else throw new ResumeNotFoundException("Resumes with this Skill not present");

		}

		else
			throw new SkillNotFoundException(" skill with the given name  not present");

	}

	public ResponseEntity<ResponseStructure<String>> DeleteResume(int resumeId) throws ResumeNotFoundException {
		    Optional<Resume> optiRes = resumeRepo.findById(resumeId);

			if (optiRes.isPresent()) {
				
				
				    resumeRepo.deleteById(resumeId);

					ResponseStructure<String> respStruc = new ResponseStructure<>();
					respStruc.setStatusCode(HttpStatus.ACCEPTED.value());
					respStruc.setMessage(" Resume data deleted successfully");
					respStruc.setData(" 1 RESUME DELETED  SUCCESSFULLY");

					return new ResponseEntity<ResponseStructure<String>>(respStruc, HttpStatus.ACCEPTED);
				}

				else
					throw new ResumeNotFoundException(" resume not found with this Id");
	}


	

}
