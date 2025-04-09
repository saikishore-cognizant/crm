package com.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.entity.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {
    
	
}