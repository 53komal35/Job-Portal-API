package com.example.jobportal.exceptionhandling;

public class EductationNotFoundException extends Exception {
	
	String mess;

	public EductationNotFoundException(String mess) {
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
