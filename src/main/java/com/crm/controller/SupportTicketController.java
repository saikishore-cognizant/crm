package com.crm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.crm.entity.SupportTicket;
import com.crm.service.impl.SupportTicketServiceImpl;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/support-tickets")
@Validated
public class SupportTicketController {
    private static final Logger logger = LoggerFactory.getLogger(SupportTicketController.class);

    @Autowired
    private SupportTicketServiceImpl service;
    
    @GetMapping
    public ResponseEntity<List<SupportTicket>> getAllTickets() {
        logger.info("Fetching all support tickets");
        List<SupportTicket> tickets = service.getAllTickets();
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupportTicket> getTicketById(@PathVariable Long id) {
        logger.info("Fetching support ticket with ID: {}", id);
        SupportTicket ticket = service.getTicketById(id);
        if (ticket != null) {
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        } else {
            logger.warn("Support ticket with ID: {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<SupportTicket> addOrUpdateTicket(@Valid @RequestBody SupportTicket ticket) {
        logger.info("Adding or updating support ticket with ID: {}", ticket.getTicketId());
        SupportTicket savedTicket = service.addOrUpdateTicket(ticket);
        return new ResponseEntity<>(savedTicket, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupportTicket> updateTicket(@PathVariable Long id, @Valid @RequestBody SupportTicket ticket) {
        logger.info("Updating support ticket with ID: {}", id);
        SupportTicket existingTicket = service.getTicketById(id);
        if (existingTicket != null) {
            ticket.setTicketId(id);
            SupportTicket updatedTicket = service.addOrUpdateTicket(ticket);
            return new ResponseEntity<>(updatedTicket, HttpStatus.OK);
        } else {
            logger.warn("Support ticket with ID: {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        logger.info("Deleting support ticket with ID: {}", id);
        SupportTicket existingTicket = service.getTicketById(id);
        if (existingTicket != null) {
            service.deleteTicket(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            logger.warn("Support ticket with ID: {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}