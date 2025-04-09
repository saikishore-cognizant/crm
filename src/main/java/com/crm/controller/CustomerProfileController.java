package com.crm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.crm.entity.CustomerProfile;
import com.crm.service.impl.CustomerProfileServiceImpl;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
@Validated
public class CustomerProfileController {
    private static final Logger logger = LoggerFactory.getLogger(CustomerProfileController.class);

    @Autowired
    private CustomerProfileServiceImpl service;

    @GetMapping
    public ResponseEntity<List<CustomerProfile>> getAllProfiles() {
        logger.info("Fetching all customer profiles");
        List<CustomerProfile> profiles = service.getAllProfiles();
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerProfile> getProfileById(@PathVariable Long id) {
        logger.info("Fetching customer profile with ID: {}", id);
        CustomerProfile profile = service.getProfileById(id);
        if (profile != null) {
            return new ResponseEntity<>(profile, HttpStatus.OK);
        } else {
            logger.warn("Customer profile with ID: {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<CustomerProfile> addOrUpdateProfile(@Valid @RequestBody CustomerProfile profile) {
        logger.info("Adding or updating customer profile with ID: {}", profile.getCustomerId());
        CustomerProfile savedProfile = service.addOrUpdateProfile(profile);
        return new ResponseEntity<>(savedProfile, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerProfile> updateProfile(@PathVariable Long id, @Valid @RequestBody CustomerProfile profile) {
        logger.info("Updating customer profile with ID: {}", id);
        CustomerProfile existingProfile = service.getProfileById(id);
        if (existingProfile != null) {
            profile.setCustomerId(id);
            CustomerProfile updatedProfile = service.addOrUpdateProfile(profile);
            return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
        } else {
            logger.warn("Customer profile with ID: {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        logger.info("Deleting customer profile with ID: {}", id);
        CustomerProfile existingProfile = service.getProfileById(id);
        if (existingProfile != null) {
            service.deleteProfile(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            logger.warn("Customer profile with ID: {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}