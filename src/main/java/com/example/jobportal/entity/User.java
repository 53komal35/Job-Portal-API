package com.example.jobportal.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.jobportal.enums.UserRole;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Component
@Entity
public class User {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int userId;
	private String username;
	private String email;
	private String password;


	private UserRole userRole;
	
	@OneToMany(mappedBy = "userMap")
	private List<Company> compMap; 
	
	@OneToOne(mappedBy = "userMap")
	private Resume resumeMap;
	
	public Resume getResumeMap() {
		return resumeMap;
	}

	public void setResumeMap(Resume resumeMap) {
		this.resumeMap = resumeMap;
	}


	public List<Company> getCompMap() {
		return compMap;
	}

	public void setCompMap(List<Company> compMap) {
		this.compMap = compMap;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

}
