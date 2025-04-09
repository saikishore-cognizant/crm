package com.crm.service.impl;

import com.crm.entity.SupportTicket;
import com.crm.repository.SupportTicketRepository;
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

public class SupportTicketServiceTestImpl {

    @Mock
    private SupportTicketRepository repository;

    @InjectMocks
    private SupportTicketServiceImpl service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllTickets() {
        List<SupportTicket> tickets = Arrays.asList(new SupportTicket(), new SupportTicket());
        when(repository.findAll()).thenReturn(tickets);

        List<SupportTicket> result = service.getAllTickets();
        assertEquals(tickets, result);
    }

    @Test
    public void testGetTicketById() {
        SupportTicket ticket = new SupportTicket();
        when(repository.findById(1L)).thenReturn(Optional.of(ticket));

        SupportTicket result = service.getTicketById(1L);
        assertEquals(ticket, result);
    }

    @Test
    public void testGetTicketByIdNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.getTicketById(1L));
    }

    @Test
    public void testAddOrUpdateTicket() {
        SupportTicket ticket = new SupportTicket();
        when(repository.save(ticket)).thenReturn(ticket);

        SupportTicket result = service.addOrUpdateTicket(ticket);
        assertEquals(ticket, result);
    }

    @Test
    public void testDeleteTicket() {
        SupportTicket ticket = new SupportTicket();
        when(repository.findById(1L)).thenReturn(Optional.of(ticket));
        doNothing().when(repository).deleteById(1L);

        service.deleteTicket(1L);
        verify(repository, times(1)).deleteById(1L);
    }
}