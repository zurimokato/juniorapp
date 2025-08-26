package com.junior.boletapp.tickets.service.impl;

import com.junior.boletapp.app.exceptions.AppException;
import com.junior.boletapp.app.exceptions.BadRequestException;
import com.junior.boletapp.app.exceptions.NotFoundException;
import com.junior.boletapp.matches.constants.MatchStatusEnum;
import com.junior.boletapp.matches.service.IMatchService;
import com.junior.boletapp.tickets.constants.TicketStatus;
import com.junior.boletapp.tickets.model.Ticket;
import com.junior.boletapp.tickets.model.TicketCategory;
import com.junior.boletapp.tickets.repository.TicketRepository;
import com.junior.boletapp.tickets.service.ITicketCategoryService;
import com.junior.boletapp.tickets.service.ITicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService implements ITicketService {
    private final TicketRepository ticketRepository;
    private final ITicketCategoryService ticketCategoryService;
    private final IMatchService matchService;

    @Override
    public Ticket addTicket(String categoryId, Ticket ticket) {
        if (categoryId == null || categoryId.isEmpty()) {
            throw new BadRequestException("Category ID cannot be null or empty");
        }
        if (ticket == null) {
            throw new BadRequestException("Ticket cannot be null");
        }
        TicketCategory category=ticketCategoryService.getCategoryById(categoryId);
        var match=matchService.getMatchById(category.getMatchId());
        if(!match.getStatus().equals(MatchStatusEnum.SCHEDULED)){
            throw new AppException("Cannot add ticket to a match that is not scheduled");
        }
        if(!category.isActive()){
            throw new AppException("Category is not active");
        }

        boolean incremented = ticketCategoryService.tryIncrementSold(categoryId, 1);
        if (!incremented) {
            throw new AppException("No more tickets available in this category");
        }

        // 3) Persistir ticket (ya hay cupo “consumido”)
        try {
            ticket.setCategoryId(categoryId);
            ticket.setStatus(TicketStatus.PAID); // o RESERVED si manejas flujo de pago
            return ticketRepository.save(ticket);
        } catch (RuntimeException e) {
            // 4) Si falló guardar el ticket, revertir el cupo (best-effort)
            ticketCategoryService.tryIncrementSold(categoryId, -1);
            throw e;
        }
    }

    @Override
    public Ticket getTicketById(String id) {
        return ticketRepository.findById(id).orElseThrow(()->new NotFoundException("Ticket not found"));
    }

    @Override
    public Ticket updateTicket(Ticket ticket) {
        Ticket existingTicket = getTicketById(ticket.getId());
        existingTicket.setCategoryId(ticket.getCategoryId());
        existingTicket.setStatus(ticket.getStatus());
        existingTicket.setDate(ticket.getDate());
        existingTicket.setSeat(ticket.getSeat());
        existingTicket.setSection(ticket.getSection());
        existingTicket.setUserId(ticket.getUserId());
        return ticketRepository.save(existingTicket);
    }

    @Override
    public List<Ticket> getAllTicketsByCategory(String categoryId) {
        return ticketRepository.findByCategoryId(categoryId);
    }

    @Override
    public void deleteTicket(String id) {
        if (id == null || id.isEmpty()) {
            throw new BadRequestException("Ticket ID cannot be null or empty");
        }
        Ticket existingTicket = getTicketById(id);
        ticketRepository.delete(existingTicket);


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
        if (categoryId == null || categoryId.isEmpty()) {
            throw new BadRequestException("Category ID cannot be null or empty");
        }
        return ticketRepository.countByCategoryIdAndStatus(categoryId, TicketStatus.PAID);
    }
}
