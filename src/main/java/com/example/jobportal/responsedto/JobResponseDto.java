package com.example.jobportal.responsedto;

import java.util.HashMap;

import org.springframework.stereotype.Component;


@Component
public class JobResponseDto {
	
	
	private int jobId;
	private String designation;
	private String jobRole;
	private String location;
	private double ctc;
	
	private HashMap<String, String> options ;

	public HashMap<String, String> getHashmap() {
		return options;
	}

	public void setHashmap(HashMap<String, String> hashmap) {
		this.options = hashmap;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

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
