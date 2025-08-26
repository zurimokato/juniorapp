package com.junior.boletapp.tickets.controller.request;

import com.junior.boletapp.tickets.constants.StadiumSection;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TicketCategoryRequest {

    private String id;

    private String name;        // Ej: "NORTE", "OCCIDENTAL ALTA", "OCC BAJ SEC6"
    private StadiumSection section;     // Ej: "NORTE", "SUR", "ORIENTAL", "OCCIDENTAL"
    private Double price;       // Valor de la boleta
    private Integer capacity;   // Opcional: número de cupos disponibles
    private int soldTickets;// Opcional: número de boletas vendidas
    @NotNull
    @NotEmpty
    private String matchId;    // Id del partido al que pertenece esta categoría de boleta
    private boolean active;
}
