package com.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.entity.SupportTicket;

@Repository
public interface SupportTicketRepository extends JpaRepository<SupportTicket, Long> {
    
	
}