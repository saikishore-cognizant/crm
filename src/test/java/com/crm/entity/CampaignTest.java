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
 
public class CampaignTest {
 
    private Validator validator;
 
    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
 
    @Test
    public void testValidCampaign() {
        Campaign campaign = new Campaign();
        campaign.setStartDate(new Date());
        campaign.setEndDate(new Date());
        campaign.setName("Campaign Name");
        campaign.setType(Campaign.Type.EMAIL);
 
        Set<ConstraintViolation<Campaign>> violations = validator.validate(campaign);
        assertTrue(violations.isEmpty());
    }
 
    @Test
    public void testInvalidCampaign() {
        Campaign campaign = new Campaign();
        campaign.setStartDate(null);
        campaign.setEndDate(null);
        campaign.setName("");
        campaign.setType(null);
 
        Set<ConstraintViolation<Campaign>> violations = validator.validate(campaign);
        assertEquals(4, violations.size());
    }
 
    @Test
    public void testEmptyName() {
        Campaign campaign = new Campaign();
        campaign.setStartDate(new Date());
        campaign.setEndDate(new Date());
        campaign.setName("");
        campaign.setType(Campaign.Type.SMS);
 
        Set<ConstraintViolation<Campaign>> violations = validator.validate(campaign);
        assertEquals(1, violations.size());
        assertEquals("Name is required", violations.iterator().next().getMessage());
    }
 
    // Add more tests for other fields and scenarios as needed
}