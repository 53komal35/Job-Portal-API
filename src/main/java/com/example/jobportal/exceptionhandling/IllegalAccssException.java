package com.example.jobportal.exceptionhandling;

public class IllegalAccssException extends Exception {
	
	String mess;

	public IllegalAccssException(String mess) {
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
