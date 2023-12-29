package com.example.jobportal.responsedto;

import java.time.LocalDate;

import java.util.HashMap;

import org.springframework.stereotype.Component;

@Component
public class WorkExperienceResponseDto {
	
	private int expId;
	
	private String description;
	private String organisation;
	private String jobRole;
	private Boolean jobStatus;
	private LocalDate startDate;
	private LocalDate endDate;
	private String yearsOfExperience;
	
	private HashMap<String,String> options;
	
	public HashMap<String, String> getOptions() {
		return options;
	}

	public void setOptions(HashMap<String, String> options) {
		this.options = options;
	}

	public int getExpId() {
		return expId;
	}

	public void setExpId(int expId) {
		this.expId = expId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOrganisation() {
		return organisation;
	}

	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}

	public String getJobRole() {
		return jobRole;
	}

	public void setJobRole(String jobRole) {
		this.jobRole = jobRole;
	}

	public Boolean getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(Boolean jobStatus) {
		this.jobStatus = jobStatus;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(String yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}



	
	

}
