package com.crm.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.entity.SupportTicket;
import com.crm.repository.SupportTicketRepository;
import com.crm.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class SupportTicketServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(SupportTicketServiceImpl.class);

    @Autowired
    private SupportTicketRepository repository;

    public List<SupportTicket> getAllTickets() {
        logger.debug("Fetching all support tickets");
        return repository.findAll();
    }

    public SupportTicket getTicketById(Long id) {
        logger.debug("Fetching support ticket with ID: {}", id);
        return repository.findById(id).orElseThrow(() -> {
            logger.warn("Support ticket not found with ID: {}", id);
            return new ResourceNotFoundException("Support ticket not found with ID: " + id);
        });
    }

    public SupportTicket addOrUpdateTicket(SupportTicket ticket) {
        logger.debug("Adding or updating support ticket with ID: {}", ticket.getTicketId());
        return repository.save(ticket);
    }

    public SupportTicket updateTicket(Long id, SupportTicket ticket) {
        logger.debug("Updating support ticket with ID: {}", id);
        SupportTicket existingTicket = repository.findById(id).orElseThrow(() -> {
            logger.warn("Support ticket not found with ID: {}", id);
            return new ResourceNotFoundException("Support ticket not found with ID: " + id);
        });
        ticket.setTicketId(id);
        ticket.setCustomerProfile(existingTicket.getCustomerProfile());
        return repository.save(ticket);
    }

    public void deleteTicket(Long id) {
        logger.debug("Deleting support ticket with ID: {}", id);
        SupportTicket existingTicket = repository.findById(id).orElseThrow(() -> {
            logger.warn("Support ticket not found with ID: {}", id);
            return new ResourceNotFoundException("Support ticket not found with ID: " + id);
        });
        repository.deleteById(id);
    }
}