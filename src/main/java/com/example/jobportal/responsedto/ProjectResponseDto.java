package com.example.jobportal.responsedto;

import org.springframework.stereotype.Component;

@Component
public class ProjectResponseDto {
	

	private int proId;
  private String projectName;
  private String[] techStack;
  private String description;
  private  String website;
  private String sourceCode;
  
  
  
  
  
public int getProId() {
	return proId;
}
public void setProId(int proId) {
	this.proId = proId;
}
public String getProjectName() {
	return projectName;
}
public void setProjectName(String projectName) {
	this.projectName = projectName;
}
public String[] getTechStack() {
	return techStack;
}
public void setTechStack(String[] techStack) {
	this.techStack = techStack;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getWebsite() {
	return website;
}
public void setWebsite(String website) {
	this.website = website;
}
public String getSourceCode() {
	return sourceCode;
}
public void setSourceCode(String sourceCode) {
	this.sourceCode = sourceCode;
}
  
  
  
  
  
}
