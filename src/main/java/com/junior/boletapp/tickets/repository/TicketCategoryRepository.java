package com.junior.boletapp.tickets.repository;

import com.junior.boletapp.tickets.constants.StadiumSection;
import com.junior.boletapp.tickets.model.TicketCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketCategoryRepository extends MongoRepository<TicketCategory, String> {
    List<TicketCategory> getAllCategoriesByMatchId(String matchId);

    boolean existsByMatchIdAndSection(String matchId, StadiumSection section);

}
