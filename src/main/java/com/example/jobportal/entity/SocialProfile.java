package com.example.jobportal.entity;

import org.springframework.stereotype.Component;

import com.example.jobportal.enums.SocialType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Component
@Entity
public class SocialProfile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sociId;
    private SocialType socialType;
    private String url;
    
    @ManyToOne
    private Resume associatedResume;
    
	
	
	
    public int getSociId() {
		return sociId;
	}

	public void setSociId(int sociId) {
		this.sociId = sociId;
	}

	public SocialType getSocialType() {
		return socialType;
	}

	public void setSocialType(SocialType socialType) {
		this.socialType = socialType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Resume getAssociatedResume() {
		return associatedResume;
	}

	public void setAssociatedResume(Resume associatedResume) {
		this.associatedResume = associatedResume;
	}


    
}
