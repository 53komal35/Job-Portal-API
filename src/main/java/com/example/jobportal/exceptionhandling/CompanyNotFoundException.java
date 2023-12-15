package com.example.jobportal.exceptionhandling;

public class CompanyNotFoundException extends Exception {
	
	String mess;

	public CompanyNotFoundException(String mess) {
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
