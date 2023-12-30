package com.example.jobportal.service;

import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.net.SocketEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.jobportal.entity.Company;
import com.example.jobportal.entity.Resume;
import com.example.jobportal.entity.SocialProfile;
import com.example.jobportal.entity.User;
import com.example.jobportal.enums.BusinessType;
import com.example.jobportal.enums.SocialType;
import com.example.jobportal.enums.UserRole;
import com.example.jobportal.exceptionhandling.IllegalAccssException;
import com.example.jobportal.exceptionhandling.ResumeNotFoundException;
import com.example.jobportal.exceptionhandling.UserNotFoundException;
import com.example.jobportal.repository.ResumeRepository;
import com.example.jobportal.repository.SocialProfileRepository;
import com.example.jobportal.requestdto.CompanyRequestDto;
import com.example.jobportal.requestdto.SocialProfileRequestDto;
import com.example.jobportal.responsedto.SocialProfileResponseDto;
import com.example.jobportal.utility.ResponseStructure;

@Service
public class SocialProfileService {
	@Autowired
	private ResumeRepository resumeRepo;
	@Autowired
	private SocialProfileRepository socialRepo;

	
	
	private SocialProfile convertSocialProfile(SocialProfileRequestDto dto,SocialProfile social)
	{
		
		social.setUrl(dto.getUrl());
		
		return social;
	}
	
	
	private SocialProfileResponseDto convertToSocialResponse(SocialProfile social)
	{
		
		SocialProfileResponseDto dto = new SocialProfileResponseDto();
		dto.setSocialType(social.getSocialType());
		dto.setSociId(social.getSociId());
		dto.setUrl(social.getUrl());
		return dto;
		
		
	}


	public ResponseEntity<ResponseStructure<String>> insertSocialProfile(SocialProfileRequestDto socReq, SocialType socialType,
			int resumeId) throws IllegalAccssException, ResumeNotFoundException 
	
	
	{                       Optional<Resume> optResume = resumeRepo.findById(resumeId);
		
		

		if (optResume.isPresent()) {
			
			Resume resume = optResume.get();
			
			     List<SocialProfile> socialList = resume.getSocialList();
			
			    
			
			if (!socialList.isEmpty()) {
				          
                              for(SocialProfile sp: socialList)
                              {
                            	  
                            	 if(sp.getSocialType()==socialType&&sp.getSocialType()==SocialType.GITHUB)
                            		 throw new IllegalAccssException("Github already present u cant enter new you can update only");
                            	 if(sp.getSocialType()==socialType&&sp.getSocialType()==SocialType.GMAIL)
                            		 throw new IllegalAccssException("Gmail already present u cant enter new you can update only");
                            	 if(sp.getSocialType()==socialType&&sp.getSocialType()==SocialType.LINKEDIN)
                            		 throw new IllegalAccssException("linkedIn already present u cant enter new you can update only");
                            	 if(sp.getSocialType()==socialType&&sp.getSocialType()==SocialType.PORTFOLIO)
                            		 throw new IllegalAccssException("potfolio already present u cant enter new you can update only");
                            	 if(sp.getSocialType()==socialType&&sp.getSocialType()==SocialType.TWITTER)
                            		 throw new IllegalAccssException("twitter already present u cant enter new you can update only");
                            	  
                              }
			}
			 SocialProfile social = convertSocialProfile(socReq, new SocialProfile());
		     social.setAssociatedResume(resume);
		     social.setSocialType(socialType);
		     socialRepo.save(social);
		     
			                 
			
				ResponseStructure<String> respStruc = new ResponseStructure<>();
				respStruc.setStatusCode(HttpStatus.CREATED.value());
				respStruc.setMessage(" Social data saved successfully");
				respStruc.setData("SOCIAL PROFILE ADDED  SUCCESSFULLY");

				return new ResponseEntity<ResponseStructure<String>>(respStruc, HttpStatus.CREATED);
			}

		else
			throw new ResumeNotFoundException("resume with given id  not present ");
	}


	

}
