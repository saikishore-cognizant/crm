package com.crm.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.entity.Campaign;
import com.crm.repository.CampaignRepository;
import com.crm.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class CampaignServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(CampaignServiceImpl.class);

    @Autowired
    private CampaignRepository repository;

    public List<Campaign> getAllCampaigns() {
        logger.debug("Fetching all campaigns");
        return repository.findAll();
    }

    public Campaign getCampaignById(Integer id) {
        logger.debug("Fetching campaign with ID: {}", id);
        return repository.findById(id).orElseThrow(() -> {
            logger.warn("Campaign not found with ID: {}", id);
            return new ResourceNotFoundException("Campaign not found with ID: " + id);
        });
    }

    public Campaign addOrUpdateCampaign(Campaign campaign) {
        logger.debug("Adding or updating campaign with ID: {}", campaign.getCampaignId());
        return repository.save(campaign);
    }

    public Campaign updateCampaign(Integer id, Campaign campaign) {
        logger.debug("Updating campaign with ID: {}", id);
        Campaign existingCampaign = repository.findById(id).orElseThrow(() -> {
            logger.warn("Campaign not found with ID: {}", id);
            return new ResourceNotFoundException("Campaign not found with ID: " + id);
        });
        campaign.setCampaignId(id);
        return repository.save(campaign);
    }

    public void deleteCampaign(Integer id) {
        logger.debug("Deleting campaign with ID: {}", id);
        Campaign existingCampaign = repository.findById(id).orElseThrow(() -> {
            logger.warn("Campaign not found with ID: {}", id);
            return new ResourceNotFoundException("Campaign not found with ID: " + id);
        });
        repository.deleteById(id);
    }
}