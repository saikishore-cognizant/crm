package com.crm.controller;

import com.crm.entity.Campaign;
import com.crm.service.impl.CampaignServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CampaignControllerTest {

    @Mock
    private CampaignServiceImpl service;

    @InjectMocks
    private CampaignController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllCampaigns() {
        List<Campaign> campaigns = Arrays.asList(new Campaign(), new Campaign());
        when(service.getAllCampaigns()).thenReturn(campaigns);

        ResponseEntity<List<Campaign>> response = controller.getAllCampaigns();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(campaigns, response.getBody());
    }

    @Test
    public void testGetCampaignById() {
        Campaign campaign = new Campaign();
        when(service.getCampaignById(1)).thenReturn(campaign);

        ResponseEntity<Campaign> response = controller.getCampaignById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(campaign, response.getBody());
    }

    @Test
    public void testAddOrUpdateCampaign() {
        Campaign campaign = new Campaign();
        when(service.addOrUpdateCampaign(campaign)).thenReturn(campaign);

        ResponseEntity<Campaign> response = controller.addOrUpdateCampaign(campaign);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(campaign, response.getBody());
    }

    @Test
    public void testUpdateCampaign() {
        Campaign campaign = new Campaign();
        when(service.getCampaignById(1)).thenReturn(campaign);
        when(service.addOrUpdateCampaign(campaign)).thenReturn(campaign);

        ResponseEntity<Campaign> response = controller.updateCampaign(1, campaign);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(campaign, response.getBody());
    }

    @Test
    public void testDeleteCampaign() {
        Campaign campaign = new Campaign();
        when(service.getCampaignById(1)).thenReturn(campaign);
        doNothing().when(service).deleteCampaign(1);

        ResponseEntity<Void> response = controller.deleteCampaign(1);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}