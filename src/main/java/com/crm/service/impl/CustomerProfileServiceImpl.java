package com.crm.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.entity.CustomerProfile;
import com.crm.repository.CustomerProfileRepository;
import com.crm.service.CustomerProfileService;
import com.crm.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class CustomerProfileServiceImpl implements CustomerProfileService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerProfileServiceImpl.class);

    @Autowired
    private CustomerProfileRepository repository;

    public List<CustomerProfile> getAllProfiles() {
        logger.debug("Fetching all customer profiles");
        return repository.findAll();
    }

    public CustomerProfile getProfileById(Long id) {
        logger.debug("Fetching customer profile with ID: {}", id);
        return repository.findById(id).orElseThrow(() -> {
            logger.warn("Customer profile not found with ID: {}", id);
            return new ResourceNotFoundException("Customer profile not found with ID: " + id);
        });
    }

    public CustomerProfile addOrUpdateProfile(CustomerProfile profile) {
        logger.debug("Adding or updating customer profile with ID: {}", profile.getCustomerId());
        return repository.save(profile);
    }

    public CustomerProfile updateProfile(Long id, CustomerProfile profile) {
        logger.debug("Updating customer profile with ID: {}", id);
        CustomerProfile existingProfile = repository.findById(id).orElseThrow(() -> {
            logger.warn("Customer profile not found with ID: {}", id);
            return new ResourceNotFoundException("Customer profile not found with ID: " + id);
        });
        profile.setCustomerId(id);
        return repository.save(profile);
    }

    public void deleteProfile(Long id) {
        logger.debug("Deleting customer profile with ID: {}", id);
        CustomerProfile existingProfile = repository.findById(id).orElseThrow(() -> {
            logger.warn("Customer profile not found with ID: {}", id);
            return new ResourceNotFoundException("Customer profile not found with ID: " + id);
        });
        repository.deleteById(id);
    }
}