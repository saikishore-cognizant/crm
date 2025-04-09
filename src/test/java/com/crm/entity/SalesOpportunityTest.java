package com.crm.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SalesOpportunityTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidSalesOpportunity() {
        SalesOpportunity opportunity = new SalesOpportunity();
        opportunity.setClosingDate(new java.sql.Date(System.currentTimeMillis()));
        opportunity.setEstimatedValue(10000.0);
        opportunity.setSalesStage("Initial Contact");

        Set<ConstraintViolation<SalesOpportunity>> violations = validator.validate(opportunity);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testInvalidSalesOpportunity() {
        SalesOpportunity opportunity = new SalesOpportunity();
        opportunity.setClosingDate(null);
        opportunity.setEstimatedValue(null);
        opportunity.setSalesStage(null);

        Set<ConstraintViolation<SalesOpportunity>> violations = validator.validate(opportunity);
        assertEquals(3, violations.size());
    }

    @Test
    public void testEmptySalesStage() {
        SalesOpportunity opportunity = new SalesOpportunity();
        opportunity.setClosingDate(new java.sql.Date(System.currentTimeMillis()));
        opportunity.setEstimatedValue(10000.0);
        opportunity.setSalesStage(null); // Use null to trigger @NotNull validation

        Set<ConstraintViolation<SalesOpportunity>> violations = validator.validate(opportunity);
        assertEquals(1, violations.size());
        assertEquals("Sales stage is required", violations.iterator().next().getMessage());
    }

    // Add more tests for other fields and scenarios as needed
}