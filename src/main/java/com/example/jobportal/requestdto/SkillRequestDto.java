package com.example.jobportal.requestdto;

import org.springframework.stereotype.Component;

@Component
public class SkillRequestDto {

	private String skillName;
	

	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
}
