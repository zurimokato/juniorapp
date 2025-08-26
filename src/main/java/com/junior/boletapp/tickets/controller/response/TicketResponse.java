package com.junior.boletapp.tickets.controller.response;

import com.junior.boletapp.tickets.constants.StadiumSection;
import com.junior.boletapp.tickets.constants.TicketStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class TicketResponse {
    private String id;
    private String seat;
    private LocalDateTime date;
    private TicketStatus status;
    private StadiumSection section;
    private String categoryId; // Id de la categoría a la que pertenece esta boleta
    private String userId;
}
