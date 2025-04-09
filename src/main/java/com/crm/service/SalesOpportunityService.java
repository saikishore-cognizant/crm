package com.crm.service;

import java.util.List;

import com.crm.entity.SalesOpportunity;

public interface SalesOpportunityService {
	public List<SalesOpportunity> getAllOpportunities();
	public SalesOpportunity getOpportunityById(Long id);
	public SalesOpportunity addOrUpdateOpportunity(SalesOpportunity opportunity);
	public SalesOpportunity updateOpportunity(Long id, SalesOpportunity opportunity);
	public void deleteOpportunity(Long id);

}
