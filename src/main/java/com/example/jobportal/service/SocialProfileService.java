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
import com.example.jobportal.entity.SocialProfile;
import com.example.jobportal.enums.SocialType;
import com.example.jobportal.exceptionhandling.IllegalAccssException;
import com.example.jobportal.exceptionhandling.ResumeNotFoundException;
import com.example.jobportal.exceptionhandling.SocialProfileNotFoundException;
import com.example.jobportal.repository.ResumeRepository;
import com.example.jobportal.repository.SocialProfileRepository;
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
					throw new IllegalAccssException("Github already present u can`t enter new instead you can update only");
				if(sp.getSocialType()==socialType&&sp.getSocialType()==SocialType.GMAIL)
					throw new IllegalAccssException("Gmail already present u can`t enter new instead you can update only");
				if(sp.getSocialType()==socialType&&sp.getSocialType()==SocialType.LINKEDIN)
					throw new IllegalAccssException("linkedIn already present u can``t enter  new instead you can update only");
				if(sp.getSocialType()==socialType&&sp.getSocialType()==SocialType.PORTFOLIO)
					throw new IllegalAccssException("potfolio already present u cant enter new insteadyou can update only");
				if(sp.getSocialType()==socialType&&sp.getSocialType()==SocialType.TWITTER)
					throw new IllegalAccssException("twitter already present u can`t enter new instead you can update only");

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


	public ResponseEntity<ResponseStructure<String>> updateSocialProfile(SocialProfileRequestDto socReq,int resumeId, int socialId) throws ResumeNotFoundException, SocialProfileNotFoundException 

	{  Optional<Resume> optResume = resumeRepo.findById(resumeId);

	if (optResume.isPresent()) {

		Resume resume = optResume.get();

		List<SocialProfile> socialList = resume.getSocialList();

		if (!socialList.isEmpty()) {

			boolean flag=false;
			for(SocialProfile sp: socialList)
			{  if(sp.getSociId()==socialId)
			{
				SocialProfile updateProfile = convertSocialProfile(socReq, sp);
				socialRepo.save(updateProfile);
				flag=true;

			}

			}

			if(flag==false) throw new SocialProfileNotFoundException("social profile with given Id not present in List");

			ResponseStructure<String> respStruc = new ResponseStructure<>();
			respStruc.setStatusCode(HttpStatus.ACCEPTED.value());
			respStruc.setMessage(" Social data updated successfully");
			respStruc.setData("SOCIAL PROFILE UPDATED  SUCCESSFULLY");

			return new ResponseEntity<ResponseStructure<String>>(respStruc, HttpStatus.ACCEPTED);
		}
		else throw new SocialProfileNotFoundException("social profiles associated with this resume not present");
	}

	else
		throw new ResumeNotFoundException("resume with given id  not present ");
	}
	
	public ResponseEntity<ResponseStructure<SocialProfileResponseDto>> findSocialProfileById(int socialId) throws SocialProfileNotFoundException 

	{  Optional<SocialProfile> optSocial = socialRepo.findById(socialId);

	if (optSocial.isPresent()) {

		                        SocialProfile profile = optSocial.get();
		                         SocialProfileResponseDto dto = convertToSocialResponse(profile);
		                         HashMap<String,String> hashMap = new HashMap<>();
		                         hashMap.put("Applicant","/resumes/"+profile.getAssociatedResume().getResumeId());
		                         dto.setOptions(hashMap);

			ResponseStructure<SocialProfileResponseDto> respStruc = new ResponseStructure<>();
			respStruc.setStatusCode(HttpStatus.ACCEPTED.value());
			respStruc.setMessage(" Social data fetched successfully");
			respStruc.setData(dto);

			return new ResponseEntity<ResponseStructure<SocialProfileResponseDto>>(respStruc, HttpStatus.ACCEPTED);
		}
		else throw new SocialProfileNotFoundException("social profiles associated with this resume not present");
	}
	
	
	public ResponseEntity<ResponseStructure<List<SocialProfileResponseDto>>> findSocialProfileByResumeId(int resumeId) throws SocialProfileNotFoundException, ResumeNotFoundException 

	{  
	
		Optional<Resume> optResume = resumeRepo.findById(resumeId);

	if (optResume.isPresent()) {
		
		                  Resume resume = optResume.get();
		                  
		                  List<SocialProfile> socialList = resume.getSocialList();
		                  
		                  if(!socialList.isEmpty())
		                  {    
                                        ArrayList<SocialProfileResponseDto> respList = new ArrayList<>();
		                          for(SocialProfile sp: socialList) {
		                         SocialProfileResponseDto dto = convertToSocialResponse(sp);
		                         HashMap<String,String> hashMap = new HashMap<>();
		                         hashMap.put("Applicant","/resumes/"+sp.getAssociatedResume().getResumeId());
		                         dto.setOptions(hashMap);
		                         respList.add(dto);
		                          }
			ResponseStructure<List<SocialProfileResponseDto>> respStruc = new ResponseStructure<>();
			respStruc.setStatusCode(HttpStatus.FOUND.value());
			respStruc.setMessage(" Social data fetched successfully");
			respStruc.setData(respList);

			return new ResponseEntity<ResponseStructure<List<SocialProfileResponseDto>>>(respStruc, HttpStatus.FOUND);
		                  }
		                  else throw new SocialProfileNotFoundException("Social profiles in given resume Not present");
		                  
		}
		else throw new ResumeNotFoundException("  resume with given id  not present");
	}

	



}
