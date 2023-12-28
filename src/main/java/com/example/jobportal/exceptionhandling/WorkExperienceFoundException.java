package com.example.jobportal.exceptionhandling;

public class WorkExperienceFoundException extends Exception {
	
	String mess;

	public WorkExperienceFoundException(String mess) {
		super();
		this.mess = mess;
	}

	public String getMess() {
		return mess;
	}

	public void setMess(String mess) {
		this.mess = mess;
	}
	
	

}
