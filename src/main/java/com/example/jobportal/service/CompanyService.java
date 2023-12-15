package com.example.jobportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.jobportal.entity.Company;
import com.example.jobportal.entity.User;
import com.example.jobportal.enums.BusinessType;
import com.example.jobportal.enums.UserRole;
import com.example.jobportal.repository.CompanyRepository;
import com.example.jobportal.repository.UserRepository;
import com.example.jobportal.requestdto.CompanyRequestDto;
import com.example.jobportal.requestdto.UserRequestDto;
import com.example.jobportal.responsedto.CompanyResponseDto;
import com.example.jobportal.responsedto.UserResponseDto;
import com.example.jobportal.utility.ResponseStructure;

@Service
public class CompanyService {
	
	@Autowired
	CompanyRepository compRepo;
	
	private Company convertToCompany(CompanyRequestDto compRq ,Company comp)
	{
	     comp.setBusinessType(compRq.getBusinessType());
	    comp.setCompanyName(compRq.getCompanyName());
	    comp.setContactEmail(compRq.getContactEmail());
	    comp.setContactPhno(compRq.getContactPhno());
	    comp.setDescription(compRq.getDescription());
	    comp.setFoundedDate(compRq.getFoundedDate());
	    comp.setWebsite(compRq.getWebsite());
	  
	    
	     
		
		return comp;
	}
	
	
	private CompanyResponseDto convertToCompResponse (Company comp)
	{
		CompanyResponseDto respDto = new CompanyResponseDto();
		respDto.setBusinessType(comp.getBusinessType());
		respDto.setCompanyId(comp.getCompanyId());
		respDto.setCompanyName(comp.getCompanyName());
		respDto.setContactEmail(comp.getContactEmail());
		respDto.setContactPhno(comp.getDescription());
		respDto.setDescription(comp.getDescription());
		respDto.setFoundedDate(comp.getFoundedDate());
		respDto.setWebsite(comp.getWebsite());
	  return respDto;
		
		
	}




	

	

	
}
