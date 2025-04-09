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

public class SupportTicketTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidSupportTicket() {
        SupportTicket ticket = new SupportTicket();
        ticket.setAssignedAgent("Agent Smith");
        ticket.setIssueDescription("Issue description details");
        ticket.setStatus(SupportTicket.Status.OPEN);

        Set<ConstraintViolation<SupportTicket>> violations = validator.validate(ticket);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testInvalidSupportTicket() {
        SupportTicket ticket = new SupportTicket();
        ticket.setAssignedAgent(null);
        ticket.setIssueDescription(null);
        ticket.setStatus(null);

        Set<ConstraintViolation<SupportTicket>> violations = validator.validate(ticket);
        assertEquals(3, violations.size());
    }

    @Test
    public void testEmptyAssignedAgent() {
        SupportTicket ticket = new SupportTicket();
        ticket.setAssignedAgent(null); // Use null to trigger @NotNull validation
        ticket.setIssueDescription("Issue description details");
        ticket.setStatus(SupportTicket.Status.OPEN);

        Set<ConstraintViolation<SupportTicket>> violations = validator.validate(ticket);
        assertEquals(1, violations.size());
        assertEquals("Assigned agent is required", violations.iterator().next().getMessage());
    }

    // Add more tests for other fields and scenarios as needed
}