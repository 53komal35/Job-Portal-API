package com.example.jobportal.exceptionhandling;

public class UserNotFoundException extends Exception {
	
	String mess;

	public UserNotFoundException(String mess) {
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
