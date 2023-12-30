package com.example.jobportal.exceptionhandling;

public class SocialProfileNotFoundException extends Exception {
	
	String mess;

	public SocialProfileNotFoundException(String mess) {
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
