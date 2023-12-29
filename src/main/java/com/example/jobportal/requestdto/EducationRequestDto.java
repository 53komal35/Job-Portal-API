package com.example.jobportal.requestdto;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.example.jobportal.enums.EducationType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Component
public class EducationRequestDto {


	private String degreeType;
	private String stream;
	@NotBlank(message=" insitutename cannot be blank")
	@NotNull(message="insitute name cannot be null")
	private String insituteName;
	
	private String location;
	@NotNull(message=" start date cannot be null")
	private LocalDate startDate;
	
	private LocalDate enddate;
	@NotNull(message=" gradStatus cannot be null")
	private Boolean gradStatus;
	
	private float percentageOrCGPA;





	public String getDegreeType() {
		return degreeType;
	}

	public void setDegreeType(String degreeType) {
		this.degreeType = degreeType;
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public String getInsituteName() {
		return insituteName;
	}

	public void setInsituteName(String insituteName) {
		this.insituteName = insituteName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEnddate() {
		return enddate;
	}

	public void setEnddate(LocalDate enddate) {
		this.enddate = enddate;
	}

	public Boolean getGradStatus() {
		return gradStatus;
	}

	public void setGradStatus(Boolean gradStatus) {
		this.gradStatus = gradStatus;
	}

	public float getPercentageOrCGPA() {
		return percentageOrCGPA;
	}

	public void setPercentageOrCGPA(float percentageOrCGPA) {
		this.percentageOrCGPA = percentageOrCGPA;
	}


}
