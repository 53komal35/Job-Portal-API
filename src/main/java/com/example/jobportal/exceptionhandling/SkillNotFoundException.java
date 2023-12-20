package com.example.jobportal.exceptionhandling;

public class SkillNotFoundException extends Exception {
	
	String mess;

	public SkillNotFoundException(String mess) {
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
