package com.junior.boletapp.tickets.repository;

import com.junior.boletapp.tickets.constants.TicketStatus;
import com.junior.boletapp.tickets.model.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends MongoRepository<Ticket,String> {

    int countByCategoryIdAndStatus(String categoryId, TicketStatus status);
    List<Ticket> findByCategoryId(String categoryId);

}
