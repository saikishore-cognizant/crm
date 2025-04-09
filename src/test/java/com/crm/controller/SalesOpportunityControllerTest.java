package com.crm.controller;

import com.crm.entity.SalesOpportunity;
import com.crm.service.impl.SalesOpportunityServiceImpl;

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

public class SalesOpportunityControllerTest {

    @Mock
    private SalesOpportunityServiceImpl service;

    @InjectMocks
    private SalesOpportunityController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllOpportunities() {
        List<SalesOpportunity> opportunities = Arrays.asList(new SalesOpportunity(), new SalesOpportunity());
        when(service.getAllOpportunities()).thenReturn(opportunities);

        ResponseEntity<List<SalesOpportunity>> response = controller.getAllOpportunities();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(opportunities, response.getBody());
    }

    @Test
    public void testGetOpportunityById() {
        SalesOpportunity opportunity = new SalesOpportunity();
        when(service.getOpportunityById(1L)).thenReturn(opportunity);

        ResponseEntity<SalesOpportunity> response = controller.getOpportunityById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(opportunity, response.getBody());
    }

    @Test
    public void testAddOrUpdateOpportunity() {
        SalesOpportunity opportunity = new SalesOpportunity();
        when(service.addOrUpdateOpportunity(opportunity)).thenReturn(opportunity);

        ResponseEntity<SalesOpportunity> response = controller.addOrUpdateOpportunity(opportunity);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(opportunity, response.getBody());
    }

    @Test
    public void testUpdateOpportunity() {
        SalesOpportunity opportunity = new SalesOpportunity();
        when(service.getOpportunityById(1L)).thenReturn(opportunity);
        when(service.addOrUpdateOpportunity(opportunity)).thenReturn(opportunity);

        ResponseEntity<SalesOpportunity> response = controller.updateOpportunity(1L, opportunity);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(opportunity, response.getBody());
    }

    @Test
    public void testDeleteOpportunity() {
        SalesOpportunity opportunity = new SalesOpportunity();
        when(service.getOpportunityById(1L)).thenReturn(opportunity);
        doNothing().when(service).deleteOpportunity(1L);

        ResponseEntity<Void> response = controller.deleteOpportunity(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}