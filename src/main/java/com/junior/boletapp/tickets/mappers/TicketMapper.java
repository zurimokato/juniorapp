package com.junior.boletapp.tickets.mappers;

import com.junior.boletapp.tickets.controller.request.TicketRequest;
import com.junior.boletapp.tickets.controller.response.TicketResponse;
import com.junior.boletapp.tickets.model.Ticket;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    TicketResponse toResponse(Ticket ticket);
    Ticket toModel(TicketRequest request);
}
