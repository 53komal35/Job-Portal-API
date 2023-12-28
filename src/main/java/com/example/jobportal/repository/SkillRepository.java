package com.example.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.jobportal.entity.Skill;

public interface SkillRepository extends JpaRepository<Skill, Integer> {
	
	@Query("select s from Skill s where s.skillName=?1")
	public Skill findSkillByname(String skillREpo);


}
