package com.example.jobportal.entity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.jobportal.enums.BusinessType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Component
@Entity
public class Company {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int  companyId ;
	private String companyName;
	private LocalDate foundedDate;
	private String description;
	private BusinessType businessType;
	private String contactEmail;
	private String contactPhno;
	private String website;
	
	@ManyToOne
	private User userMap;
	
	
	@OneToMany(mappedBy = "compMap")
	private List<Job> jobMap;
	
	
	public List<Job> getJobMap() {
		return jobMap;
	}
	public void setJobMap(List<Job> jobMap) {
		this.jobMap = jobMap;
	}

	
	public User getUserMap() {
		return userMap;
	}
	public void setUserMap(User userMap) {
		this.userMap = userMap;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public LocalDate getFoundedDate() {
		return foundedDate;
	}
	public void setFoundedDate(LocalDate foundedDate) {
		this.foundedDate = foundedDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BusinessType getBusinessType() {
		return businessType;
	}
	public void setBusinessType(BusinessType businessType) {
		this.businessType = businessType;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	public String getContactPhno() {
		return contactPhno;
	}
	public void setContactPhno(String contactPhno) {
		this.contactPhno = contactPhno;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	
	
	
	
	

}
