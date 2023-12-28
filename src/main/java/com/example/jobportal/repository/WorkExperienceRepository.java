package com.example.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jobportal.entity.WorkExperience;

public interface WorkExperienceRepository extends JpaRepository<WorkExperience, Integer> {

//	@Query("select COALESCE(DATEDIFF(w.endDate=?2, w.startDate=?1), DATEDIFF(CURRENT_DATE, w.startDate=?1))FROM  WorkExperience  w")
//	  public float yearsBetween(LocalDate startdate,LocalDate endDate);
//	
}
