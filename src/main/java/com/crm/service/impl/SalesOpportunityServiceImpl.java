package com.crm.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.entity.SalesOpportunity;
import com.crm.repository.SalesOpportunityRepository;
import com.crm.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class SalesOpportunityServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(SalesOpportunityServiceImpl.class);

    @Autowired
    private SalesOpportunityRepository repository;

    public List<SalesOpportunity> getAllOpportunities() {
        logger.debug("Fetching all sales opportunities");
        return repository.findAll();
    }

    public SalesOpportunity getOpportunityById(Long id) {
        logger.debug("Fetching sales opportunity with ID: {}", id);
        return repository.findById(id).orElseThrow(() -> {
            logger.warn("Sales opportunity not found with ID: {}", id);
            return new ResourceNotFoundException("Sales opportunity not found with ID: " + id);
        });
    }

    public SalesOpportunity addOrUpdateOpportunity(SalesOpportunity opportunity) {
        logger.debug("Adding or updating sales opportunity with ID: {}", opportunity.getOpportunityId());
        return repository.save(opportunity);
    }

    public SalesOpportunity updateOpportunity(Long id, SalesOpportunity opportunity) {
        logger.debug("Updating sales opportunity with ID: {}", id);
        SalesOpportunity existingOpportunity = repository.findById(id).orElseThrow(() -> {
            logger.warn("Sales opportunity not found with ID: {}", id);
            return new ResourceNotFoundException("Sales opportunity not found with ID: " + id);
        });
        opportunity.setOpportunityId(id);
        return repository.save(opportunity);
    }

    public void deleteOpportunity(Long id) {
        logger.debug("Deleting sales opportunity with ID: {}", id);
        SalesOpportunity existingOpportunity = repository.findById(id).orElseThrow(() -> {
            logger.warn("Sales opportunity not found with ID: {}", id);
            return new ResourceNotFoundException("Sales opportunity not found with ID: " + id);
        });
        repository.deleteById(id);
    }
}