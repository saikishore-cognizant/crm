package com.crm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.crm.entity.Campaign;
import com.crm.service.impl.CampaignServiceImpl;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/campaigns")
@Validated
public class CampaignController {
    private static final Logger logger = LoggerFactory.getLogger(CampaignController.class);

    @Autowired
    private CampaignServiceImpl service;

    @GetMapping
    public ResponseEntity<List<Campaign>> getAllCampaigns() {
        logger.info("Fetching all campaigns");
        List<Campaign> campaigns = service.getAllCampaigns();
        return new ResponseEntity<>(campaigns, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Campaign> getCampaignById(@PathVariable Integer id) {
        logger.info("Fetching campaign with ID: {}", id);
        Campaign campaign = service.getCampaignById(id);
        if (campaign != null) {
            return new ResponseEntity<>(campaign, HttpStatus.OK);
        } else {
            logger.warn("Campaign with ID: {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Campaign> addOrUpdateCampaign(@Valid @RequestBody Campaign campaign) {
        logger.info("Adding or updating campaign with ID: {}", campaign.getCampaignId());
        Campaign savedCampaign = service.addOrUpdateCampaign(campaign);
        return new ResponseEntity<>(savedCampaign, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Campaign> updateCampaign(@PathVariable Integer id, @Valid @RequestBody Campaign campaign) {
        logger.info("Updating campaign with ID: {}", id);
        Campaign existingCampaign = service.getCampaignById(id);
        if (existingCampaign != null) {
            campaign.setCampaignId(id);
            Campaign updatedCampaign = service.addOrUpdateCampaign(campaign);
            return new ResponseEntity<>(updatedCampaign, HttpStatus.OK);
        } else {
            logger.warn("Campaign with ID: {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCampaign(@PathVariable Integer id) {
        logger.info("Deleting campaign with ID: {}", id);
        Campaign existingCampaign = service.getCampaignById(id);
        if (existingCampaign != null) {
            service.deleteCampaign(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            logger.warn("Campaign with ID: {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}