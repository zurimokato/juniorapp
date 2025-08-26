package com.junior.boletapp.tickets.service.impl;

import com.junior.boletapp.app.exceptions.BadRequestException;
import com.junior.boletapp.tickets.model.Ticket;
import com.junior.boletapp.tickets.repository.TicketRepository;
import com.junior.boletapp.tickets.service.ITicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketService implements ITicketService {
    private final TicketRepository ticketRepository;
    @Override
    public Ticket addTicket(Ticket ticket) {
        return null;
    }

    @Override
    public Ticket getTicketById(String id) {
        return null;
    }

    @Override
    public Ticket updateTicket(Ticket ticket) {
        return null;
    }

    @Override
    public void deleteTicket(String id) {
        if (id == null || id.isEmpty()) {
            throw new BadRequestException("Ticket ID cannot be null or empty");
        }


    }

    @Override
    public Ticket reserveTicket(String id) {
        return null;
    }

    @Override
    public Ticket cancelReservation(String id) {
        return null;
    }

    @Override
    public int getNumberOfTicketsByCategorySold(String categoryId) {
        if(categoryId == null || categoryId.isEmpty()){
            throw new BadRequestException("Category ID cannot be null or empty");
        }
        return 0;
    }
}
