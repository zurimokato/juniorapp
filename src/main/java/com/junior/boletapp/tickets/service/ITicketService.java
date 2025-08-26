package com.junior.boletapp.tickets.service;

import com.junior.boletapp.tickets.model.Ticket;

import java.util.List;

public interface ITicketService {
    Ticket addTicket(String categoryId,Ticket ticket);
    Ticket getTicketById(String id);
    Ticket updateTicket(Ticket ticket);
    List<Ticket> getAllTicketsByCategory(String categoryId);
    void deleteTicket(String id);
    Ticket reserveTicket(String id);
    Ticket cancelReservation(String id);
    int getNumberOfTicketsByCategorySold(String categoryId);
}
