package com.example.jobportal.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.jobportal.entity.Company;
import com.example.jobportal.entity.User;
import com.example.jobportal.enums.BusinessType;
import com.example.jobportal.enums.UserRole;
import com.example.jobportal.exceptionhandling.IllegalAccssException;
import com.example.jobportal.exceptionhandling.UserNotFoundException;
import com.example.jobportal.repository.CompanyRepository;
import com.example.jobportal.repository.UserRepository;
import com.example.jobportal.requestdto.CompanyRequestDto;
import com.example.jobportal.responsedto.CompanyResponseDto;
import com.example.jobportal.utility.ResponseStructure;

@Service
public class CompanyService {
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private CompanyRepository compRepo;

	private Company convertToCompany(CompanyRequestDto compRq, Company comp) {
		comp.setBusinessType(compRq.getBusinessType());
		comp.setCompanyName(compRq.getCompanyName());
		comp.setContactEmail(compRq.getContactEmail());
		comp.setContactPhno(compRq.getContactPhno());
		comp.setDescription(compRq.getDescription());
		comp.setFoundedDate(compRq.getFoundedDate());
		comp.setWebsite(compRq.getWebsite());

		return comp;
	}

	private CompanyResponseDto convertToCompResponse(Company comp) {
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

	public ResponseEntity<ResponseStructure<String>> insertCompany(CompanyRequestDto compReq, BusinessType bussType,
			int userId) throws IllegalAccssException, UserNotFoundException {
		Optional<User> optUser = userRepo.findById(userId);

		if (optUser.isPresent()) {
			User user = optUser.get();
			if (user.getUserRole() == UserRole.EMPLOYER) {

				Company company = convertToCompany(compReq, new Company());
				company.setBusinessType(bussType);

				compRepo.save(company);

				ResponseStructure<String> respStruc = new ResponseStructure<>();
				respStruc.setStatusCode(HttpStatus.CREATED.value());
				respStruc.setMessage(" Company data saved successfully");
				respStruc.setData(" 1 COMPANY ADDED  SUCCESSFULLY");

				return new ResponseEntity<ResponseStructure<String>>(respStruc, HttpStatus.CREATED);
			}

			else
				throw new IllegalAccssException(" This user not Autharised to add Companies");

		} else
			throw new UserNotFoundException("user not found");
	}

}
