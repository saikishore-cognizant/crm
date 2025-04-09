package com.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.entity.SalesOpportunity;

@Repository
public interface SalesOpportunityRepository extends JpaRepository<SalesOpportunity, Long> {

}