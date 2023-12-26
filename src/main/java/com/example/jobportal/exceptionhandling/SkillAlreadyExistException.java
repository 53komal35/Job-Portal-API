package com.example.jobportal.exceptionhandling;

public class SkillAlreadyExistException extends Exception {
	
	String mess;

	public SkillAlreadyExistException(String mess) {
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
