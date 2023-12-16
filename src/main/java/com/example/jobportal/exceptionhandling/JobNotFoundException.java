package com.example.jobportal.exceptionhandling;

public class JobNotFoundException extends Exception {
	
	String mess;

	public JobNotFoundException(String mess) {
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
