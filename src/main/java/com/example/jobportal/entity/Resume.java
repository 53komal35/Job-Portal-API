package com.example.jobportal.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Component
@Entity
public class Resume {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int resumeId;
	private String objective;
	
	@OneToOne
	private User userMap;
	
	@ManyToMany
	private List<Skill> skillMap;
	
	@OneToMany(mappedBy = "resumeMap")
	private List<Project> projectMap;
	
	@OneToMany(mappedBy = "associatedResume")
	private List<WorkExperience> workList;
	
	public List<SocialProfile> getSocialList() {
		return socialList;
	}

	public void setSocialList(List<SocialProfile> socialList) {
		this.socialList = socialList;
	}

	@OneToMany(mappedBy = "associatedResume")
	private List<Education> eduList;
	
	@OneToMany(mappedBy = "associatedResume")
	private List<SocialProfile> socialList;
	
	
	public List<Education> getEduList() {
		return eduList;
	}

	public void setEduList(List<Education> eduList) {
		this.eduList = eduList;
	}

	public List<WorkExperience> getWorkList() {
		return workList;
	}

	public void setWorkList(List<WorkExperience> workList) {
		this.workList = workList;
	}

	public List<Project> getProjectMap() {
		return projectMap;
	}

	public void setProjectMap(List<Project> projectMap) {
		this.projectMap = projectMap;
	}

	public User getUserMap() {
		return userMap;
	}

	public void setUserMap(User userMap) {
		this.userMap = userMap;
	}



	public int getResumeId() {
		return resumeId;
	}

	public void setResumeId(int resumeId) {
		this.resumeId = resumeId;
	}

	public String getObjective() {
		return objective;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}

	public List<Skill> getSkillMap() {
		return skillMap;
	}

	public void setSkillMap(List<Skill> skillMap) {
		this.skillMap = skillMap;
	}
	

}
