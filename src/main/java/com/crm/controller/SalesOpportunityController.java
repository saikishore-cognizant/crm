package com.crm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.crm.entity.SalesOpportunity;
import com.crm.service.impl.SalesOpportunityServiceImpl;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/sales-opportunities")
@Validated
public class SalesOpportunityController {
    private static final Logger logger = LoggerFactory.getLogger(SalesOpportunityController.class);

    @Autowired
    private SalesOpportunityServiceImpl service;

    @GetMapping
    public ResponseEntity<List<SalesOpportunity>> getAllOpportunities() {
        logger.info("Fetching all sales opportunities");
        List<SalesOpportunity> opportunities = service.getAllOpportunities();
        return new ResponseEntity<>(opportunities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalesOpportunity> getOpportunityById(@PathVariable Long id) {
        logger.info("Fetching sales opportunity with ID: {}", id);
        SalesOpportunity opportunity = service.getOpportunityById(id);
        if (opportunity != null) {
            return new ResponseEntity<>(opportunity, HttpStatus.OK);
        } else {
            logger.warn("Sales opportunity with ID: {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<SalesOpportunity> addOrUpdateOpportunity(@Valid @RequestBody SalesOpportunity opportunity) {
        logger.info("Adding or updating sales opportunity with ID: {}", opportunity.getOpportunityId());
        SalesOpportunity savedOpportunity = service.addOrUpdateOpportunity(opportunity);
        return new ResponseEntity<>(savedOpportunity, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalesOpportunity> updateOpportunity(@PathVariable Long id, @Valid @RequestBody SalesOpportunity opportunity) {
        logger.info("Updating sales opportunity with ID: {}", id);
        SalesOpportunity existingOpportunity = service.getOpportunityById(id);
        if (existingOpportunity != null) {
            opportunity.setOpportunityId(id);
            SalesOpportunity updatedOpportunity = service.addOrUpdateOpportunity(opportunity);
            return new ResponseEntity<>(updatedOpportunity, HttpStatus.OK);
        } else {
            logger.warn("Sales opportunity with ID: {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOpportunity(@PathVariable Long id) {
        logger.info("Deleting sales opportunity with ID: {}", id);
        SalesOpportunity existingOpportunity = service.getOpportunityById(id);
        if (existingOpportunity != null) {
            service.deleteOpportunity(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            logger.warn("Sales opportunity with ID: {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}