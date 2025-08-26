package com.junior.boletapp.tickets.controller.request;

import com.junior.boletapp.tickets.constants.StadiumSection;
import com.junior.boletapp.tickets.constants.TicketStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class TicketRequest {

    private String id;
    private String seat;
    private LocalDateTime date;
    private TicketStatus status;
    private StadiumSection section;
    @NotNull
    @NotEmpty
    private String categoryId; // Id de la categoría a la que pertenece esta boleta
    @NotNull
    @NotEmpty
    private String userId;
}
