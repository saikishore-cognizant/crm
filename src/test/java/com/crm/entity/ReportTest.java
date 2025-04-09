package com.crm.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReportTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidReport() {
        Report report = new Report();
        report.setGeneratedDate(new Date());
        report.setReportType(Report.ReportType.SALES);
        report.setDataPoints("Data points details");

        Set<ConstraintViolation<Report>> violations = validator.validate(report);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testInvalidReport() {
        Report report = new Report();
        report.setGeneratedDate(null);
        report.setReportType(null);
        report.setDataPoints("Data points details");

        Set<ConstraintViolation<Report>> violations = validator.validate(report);
        assertEquals(2, violations.size());
    }

    @Test
    public void testEmptyReportType() {
        Report report = new Report();
        report.setGeneratedDate(new Date());
        report.setReportType(null);
        report.setDataPoints("Data points details");

        Set<ConstraintViolation<Report>> violations = validator.validate(report);
        assertEquals(1, violations.size());
        assertEquals("Report type is required", violations.iterator().next().getMessage());
    }

    // Add more tests for other fields and scenarios as needed
}