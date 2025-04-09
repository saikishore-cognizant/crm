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

public class CustomerProfileTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidCustomerProfile() {
        CustomerProfile profile = new CustomerProfile();
        profile.setName("John Doe");
        profile.setContactInfo("john.doe@example.com");
        profile.setPurchaseHistory("Purchase history details");
        profile.setSegmentationData("Segmentation data details");

        Set<ConstraintViolation<CustomerProfile>> violations = validator.validate(profile);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testInvalidCustomerProfile() {
        CustomerProfile profile = new CustomerProfile();
        profile.setName("");
        profile.setContactInfo("");
        profile.setPurchaseHistory("");
        profile.setSegmentationData("");

        Set<ConstraintViolation<CustomerProfile>> violations = validator.validate(profile);
        assertEquals(4, violations.size());
    }

    @Test
    public void testEmptyName() {
        CustomerProfile profile = new CustomerProfile();
        profile.setName("");
        profile.setContactInfo("john.doe@example.com");
        profile.setPurchaseHistory("Purchase history details");
        profile.setSegmentationData("Segmentation data details");

        Set<ConstraintViolation<CustomerProfile>> violations = validator.validate(profile);
        assertEquals(1, violations.size());
        assertEquals("Name cannot be empty", violations.iterator().next().getMessage());
    }

}