package com.example.jobportal.requestdto;

import java.time.LocalDate;


import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Component
public class WorkExperienceRequestDto {

	
	private String description;
	@NotBlank(message = "organissation cannot be blank")
	@NotNull(message = "organisation cannot be null")
	private String organisation;
	@NotBlank(message = " jobRole cannot be blank")
	@NotNull(message = "jobRole  cannot be null")
	private String jobRole;
	@NotNull(message = "jobStatus   cannot be null")
	private Boolean jobStatus;
	@NotNull(message = " start  cannot be null")
	private LocalDate startDate;
	
	
	
	private LocalDate endDate;
	
	
	private String yearsOfExperience;
	



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
