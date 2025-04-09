package com.crm.service;

import java.util.List;

import com.crm.entity.SupportTicket;

public interface SupportTicketService {
	public List<SupportTicket> getAllTickets();
	public SupportTicket getTicketById(Long id);
	public SupportTicket addOrUpdateTicket(SupportTicket ticket);
	public SupportTicket updateTicket(Long id, SupportTicket ticket);
	public void deleteTicket(Long id);
	

}
