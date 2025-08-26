package com.junior.boletapp.tickets.mappers;

import com.junior.boletapp.tickets.controller.request.TicketCategoryRequest;
import com.junior.boletapp.tickets.controller.response.TicketCategoryResponse;
import com.junior.boletapp.tickets.model.TicketCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TicketCategoryMapper {
    TicketCategoryResponse toResponse(TicketCategory ticketCategory);
    TicketCategory toModel(TicketCategoryRequest ticketCategoryRequest);
}
