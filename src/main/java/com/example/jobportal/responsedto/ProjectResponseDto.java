package com.example.jobportal.responsedto;

import java.util.HashMap;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class ProjectResponseDto {
	

	private int proId;
  private String projectName;
  private Set<String> techStack;
  private String description;
  private  String website;
  private String sourceCode;
  
  private HashMap<String, String> options;
  
  
  
public HashMap<String, String> getOptions() {
	return options;
}
public void setOptions(HashMap<String, String> options) {
	this.options = options;
}
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
public Set<String> getTechStack() {
	return techStack;
}
public void setTechStack(Set<String> techStack) {
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
