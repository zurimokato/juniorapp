package com.junior.boletapp.tickets.service;

import com.junior.boletapp.tickets.model.Ticket;

public interface ITicketService {
    Ticket addTicket(Ticket ticket);
    Ticket getTicketById(String id);
    Ticket updateTicket(Ticket ticket);
    void deleteTicket(String id);
    Ticket reserveTicket(String id);
    Ticket cancelReservation(String id);
    int getNumberOfTicketsByCategorySold(String categoryId);
}
