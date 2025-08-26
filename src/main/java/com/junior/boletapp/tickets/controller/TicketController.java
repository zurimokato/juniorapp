package com.junior.boletapp.tickets.controller;

import com.junior.boletapp.tickets.controller.api.TicketApi;
import com.junior.boletapp.tickets.controller.request.TicketRequest;
import com.junior.boletapp.tickets.controller.response.TicketResponse;
import com.junior.boletapp.tickets.mappers.TicketMapper;
import com.junior.boletapp.tickets.service.ITicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TicketController implements TicketApi {
    private final TicketMapper ticketMapper;
    private final ITicketService ticketService;
    @Override
    public ResponseEntity<TicketResponse> createTicket(String categoryID, TicketRequest ticket) {
        TicketResponse response= ticketMapper.toResponse(
                ticketService.addTicket(categoryID, ticketMapper.toModel(ticket))
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<TicketResponse> getTicketById(String id) {
        TicketResponse response= ticketMapper.toResponse(
                ticketService.getTicketById(id)
        );
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<TicketResponse>> getTicketsByCategory(String categoryId) {
        List<TicketResponse>responses= ticketService.getAllTicketsByCategory(categoryId).stream().map(ticketMapper::toResponse).toList();
        return ResponseEntity.ok(responses);
    }

    @Override
    public ResponseEntity<TicketResponse> updateTicket(TicketRequest ticket) {
        TicketResponse response= ticketMapper.toResponse(
                ticketService.updateTicket(ticketMapper.toModel(ticket))
        );
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deleteTicket(String id) {
        ticketService.deleteTicket(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Integer> getNumberOfTicketsByCategorySold(String categoryId) {
        int numberOfTicketsSold=ticketService.getNumberOfTicketsByCategorySold(categoryId);
        return ResponseEntity.ok(numberOfTicketsSold);
    }
}
