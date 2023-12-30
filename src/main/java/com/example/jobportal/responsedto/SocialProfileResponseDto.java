package com.example.jobportal.responsedto;

import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.example.jobportal.enums.SocialType;

@Component
public class SocialProfileResponseDto {
	
	
	private int sociId;
    private SocialType socialType;
    private String url;
    private HashMap<String, String> options;

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

	public HashMap<String, String> getOptions() {
		return options;
	}

	public void setOptions(HashMap<String, String> options) {
		this.options = options;
	}




    
}
