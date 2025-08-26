package com.junior.boletapp.tickets.service;

import com.junior.boletapp.tickets.model.TicketCategory;

import java.util.List;

public interface ITicketCategoryService {

    TicketCategory addCategory(TicketCategory category);
    TicketCategory getCategoryById(String id);
    TicketCategory updateCategory(String id,TicketCategory category);
    void deleteCategory(String id);
    List<TicketCategory> getAllCategoriesByMatch(String matchId);

}
