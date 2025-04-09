package com.crm.controller;

import com.crm.entity.SupportTicket;
import com.crm.service.impl.SupportTicketServiceImpl;

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

public class SupportTicketControllerTest {

    @Mock
    private SupportTicketServiceImpl service;

    @InjectMocks
    private SupportTicketController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllTickets() {
        List<SupportTicket> tickets = Arrays.asList(new SupportTicket(), new SupportTicket());
        when(service.getAllTickets()).thenReturn(tickets);

        ResponseEntity<List<SupportTicket>> response = controller.getAllTickets();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tickets, response.getBody());
    }

    @Test
    public void testGetTicketById() {
        SupportTicket ticket = new SupportTicket();
        when(service.getTicketById(1L)).thenReturn(ticket);

        ResponseEntity<SupportTicket> response = controller.getTicketById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ticket, response.getBody());
    }

    @Test
    public void testAddOrUpdateTicket() {
        SupportTicket ticket = new SupportTicket();
        when(service.addOrUpdateTicket(ticket)).thenReturn(ticket);

        ResponseEntity<SupportTicket> response = controller.addOrUpdateTicket(ticket);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(ticket, response.getBody());
    }

    @Test
    public void testUpdateTicket() {
        SupportTicket ticket = new SupportTicket();
        when(service.getTicketById(1L)).thenReturn(ticket);
        when(service.addOrUpdateTicket(ticket)).thenReturn(ticket);

        ResponseEntity<SupportTicket> response = controller.updateTicket(1L, ticket);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ticket, response.getBody());
    }

    @Test
    public void testDeleteTicket() {
        SupportTicket ticket = new SupportTicket();
        when(service.getTicketById(1L)).thenReturn(ticket);
        doNothing().when(service).deleteTicket(1L);

        ResponseEntity<Void> response = controller.deleteTicket(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}