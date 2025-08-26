package com.junior.boletapp.tickets.validation;

import com.junior.boletapp.app.exceptions.BadRequestException;
import com.junior.boletapp.tickets.model.TicketCategory;

public class TicketCategoryValidation {

    public static void validate(TicketCategory ticketCategory){
        if(ticketCategory==null){
            throw new BadRequestException("Category cannot be null");
        }
        if(ticketCategory.getMatchId()==null || ticketCategory.getMatchId().isEmpty()){
            throw new BadRequestException("Match ID cannot be null or empty");
        }

    }

    private TicketCategoryValidation(){

    }
}
