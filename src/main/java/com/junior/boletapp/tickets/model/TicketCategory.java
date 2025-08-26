package com.junior.boletapp.tickets.model;

import com.junior.boletapp.tickets.constants.StadiumSection;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@Document(collection = "ticket_categories")
public class TicketCategory {

    @Id
    private String id;

    private String name;        // Ej: "NORTE", "OCCIDENTAL ALTA", "OCC BAJ SEC6"
    private StadiumSection section;     // Ej: "NORTE", "SUR", "ORIENTAL", "OCCIDENTAL"
    private Double price;       // Valor de la boleta
    private Integer capacity;   // Opcional: número de cupos disponibles
    private int soldTickets;// Opcional: número de boletas vendidas
    private String matchId;    // Id del partido al que pertenece esta categoría de boleta
    private boolean active; // Indica si la categoría está activa o inactiva
}
