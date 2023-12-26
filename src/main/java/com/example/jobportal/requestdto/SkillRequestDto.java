package com.example.jobportal.requestdto;

import org.springframework.stereotype.Component;

@Component
public class SkillRequestDto {

	private String[] skills;

	public String[] getSkills() {
		return skills;
	}

	public void setSkills(String[] skills) {
		this.skills = skills;
	}
	


}
