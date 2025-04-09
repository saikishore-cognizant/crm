package com.crm.service.impl;

import com.crm.entity.SalesOpportunity;
import com.crm.repository.SalesOpportunityRepository;
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

public class SalesOpportunityServiceTestImpl {

    @Mock
    private SalesOpportunityRepository repository;

    @InjectMocks
    private SalesOpportunityServiceImpl service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllOpportunities() {
        List<SalesOpportunity> opportunities = Arrays.asList(new SalesOpportunity(), new SalesOpportunity());
        when(repository.findAll()).thenReturn(opportunities);

        List<SalesOpportunity> result = service.getAllOpportunities();
        assertEquals(opportunities, result);
    }

    @Test
    public void testGetOpportunityById() {
        SalesOpportunity opportunity = new SalesOpportunity();
        when(repository.findById(1L)).thenReturn(Optional.of(opportunity));

        SalesOpportunity result = service.getOpportunityById(1L);
        assertEquals(opportunity, result);
    }

    @Test
    public void testGetOpportunityByIdNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.getOpportunityById(1L));
    }

    @Test
    public void testAddOrUpdateOpportunity() {
        SalesOpportunity opportunity = new SalesOpportunity();
        when(repository.save(opportunity)).thenReturn(opportunity);

        SalesOpportunity result = service.addOrUpdateOpportunity(opportunity);
        assertEquals(opportunity, result);
    }

    @Test
    public void testDeleteOpportunity() {
        SalesOpportunity opportunity = new SalesOpportunity();
        when(repository.findById(1L)).thenReturn(Optional.of(opportunity));
        doNothing().when(repository).deleteById(1L);

        service.deleteOpportunity(1L);
        verify(repository, times(1)).deleteById(1L);
    }
}