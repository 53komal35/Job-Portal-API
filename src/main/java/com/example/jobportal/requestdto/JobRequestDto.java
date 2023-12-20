package com.example.jobportal.requestdto;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Component
public class JobRequestDto {
	
	@NotBlank(message = "designation cannot be blank")
	@NotNull(message = "designation cannot be null")
	private String designation;
	
	@NotBlank(message=" Role description cannot be blank")
	private String jobRole;
	
	private String location;
	
	private double ctc;
	


	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getJobRole() {
		return jobRole;
	}

	public void setJobRole(String jobRole) {
		this.jobRole = jobRole;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getCtc() {
		return ctc;
	}

	public void setCtc(double ctc) {
		this.ctc = ctc;
	}



	
	
}
