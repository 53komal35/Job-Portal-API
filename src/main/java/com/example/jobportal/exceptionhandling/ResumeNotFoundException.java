package com.example.jobportal.exceptionhandling;

public class ResumeNotFoundException extends Exception {
	
	String mess;

	public ResumeNotFoundException(String mess) {
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
