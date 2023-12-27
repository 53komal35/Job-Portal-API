package com.example.jobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jobportal.entity.Resume;
import com.example.jobportal.entity.Skill;

public interface ResumeRepository extends JpaRepository<Resume, Integer> {
	
//	@Query("select r from Resume r where r.skillMap.skillId")
//	public List<Resume> findresumeBySkillname(int skillId);
//	
//	@Query("select r from Resume r where r.skillMap=?1")       // no need to fire Query  to find the By the Variable Name
	public List<Resume> findAllBySkillMap(Skill skill);

}
