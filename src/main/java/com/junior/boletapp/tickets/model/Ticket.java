package com.junior.boletapp.tickets.model;

import com.junior.boletapp.tickets.constants.StadiumSection;
import com.junior.boletapp.tickets.constants.TicketStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Document(collection = "tickets")
public class Ticket {
    @Id
    private String id;
    private String seat;
    private LocalDateTime date;
    private TicketStatus status;
    private StadiumSection section;
    private String categoryId; // Id de la categoría a la que pertenece esta boleta
    private String userId;     // Id del usuario que reservó o compró la boleta

}
