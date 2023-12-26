package com.example.jobportal.exceptionhandling;

public class ProjectNotFoundException extends Exception {
	
	String mess;

	public ProjectNotFoundException(String mess) {
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
