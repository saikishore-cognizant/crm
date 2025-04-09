package com.crm.service.impl;

import com.crm.entity.Campaign;
import com.crm.repository.CampaignRepository;
import com.crm.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CampaignServiceTestImpl {

    @Mock
    private CampaignRepository repository;

    @InjectMocks
    private CampaignServiceImpl service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllCampaigns() {
        List<Campaign> campaigns = Arrays.asList(new Campaign(), new Campaign());
        when(repository.findAll()).thenReturn(campaigns);

        List<Campaign> result = service.getAllCampaigns();
        assertEquals(campaigns, result);
    }

    @Test
    public void testGetCampaignById() {
        Campaign campaign = new Campaign();
        when(repository.findById(1)).thenReturn(Optional.of(campaign));

        Campaign result = service.getCampaignById(1);
        assertEquals(campaign, result);
    }

    @Test
    public void testGetCampaignByIdNotFound() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.getCampaignById(1));
    }

    @Test
    public void testAddOrUpdateCampaign() {
        Campaign campaign = new Campaign();
        when(repository.save(campaign)).thenReturn(campaign);

        Campaign result = service.addOrUpdateCampaign(campaign);
        assertEquals(campaign, result);
    }

    @Test
    public void testDeleteCampaign() {
        Campaign campaign = new Campaign();
        when(repository.findById(1)).thenReturn(Optional.of(campaign));
        doNothing().when(repository).deleteById(1);

        service.deleteCampaign(1);
        verify(repository, times(1)).deleteById(1);
    }
}