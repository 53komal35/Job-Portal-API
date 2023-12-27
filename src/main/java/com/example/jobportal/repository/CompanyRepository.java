package com.example.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.jobportal.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {


}
