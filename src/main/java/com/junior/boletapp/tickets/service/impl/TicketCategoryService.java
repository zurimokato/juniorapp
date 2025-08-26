package com.junior.boletapp.tickets.service.impl;

import com.junior.boletapp.app.exceptions.BadRequestException;
import com.junior.boletapp.app.exceptions.NotFoundException;
import com.junior.boletapp.matches.constants.MatchStatusEnum;
import com.junior.boletapp.matches.model.Match;
import com.junior.boletapp.matches.service.IMatchService;
import com.junior.boletapp.tickets.model.TicketCategory;
import com.junior.boletapp.tickets.repository.TicketCategoryRepository;
import com.junior.boletapp.tickets.service.ITicketCategoryService;
import com.junior.boletapp.tickets.validation.TicketCategoryValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketCategoryService implements ITicketCategoryService {

    private final TicketCategoryRepository ticketCategoryRepository;
    private final IMatchService matchService;


    @Override
    public TicketCategory addCategory(TicketCategory category) {
        TicketCategoryValidation.validate(category);
        Match match = matchService.getMatchById(category.getMatchId());
        if (match.getStatus() != MatchStatusEnum.SCHEDULED) {
            throw new BadRequestException("Cannot add category to a match that is not scheduled");
        }

        return ticketCategoryRepository.save(category);
    }

    @Override
    public TicketCategory getCategoryById(String id) {
        if (id == null || id.isEmpty()) {
            throw new BadRequestException("Category ID cannot be null or empty");
        }

        return ticketCategoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found"));
    }

    @Override
    public TicketCategory updateCategory(String ticketCategoryId, TicketCategory category) {
        TicketCategoryValidation.validate(category);
        if ( category.getId() == null || category.getId().isEmpty()) {
            throw new BadRequestException("Category or Category ID cannot be null or empty");
        }

        Match match = matchService.getMatchById(category.getMatchId());
        if (match.getStatus() != MatchStatusEnum.SCHEDULED) {
            throw new BadRequestException("Cannot add category to a match that is not scheduled");
        }
        var oldCategory = ticketCategoryRepository.findById(ticketCategoryId)
                .orElseThrow(() -> new NotFoundException("Category not found"));
        oldCategory.setName(category.getName());
        oldCategory.setPrice(category.getPrice());
        oldCategory.setActive(category.isActive());
        oldCategory.setSoldTickets(category.getSoldTickets());
        oldCategory.setSection(category.getSection());
        oldCategory.setCapacity(category.getCapacity());
        return ticketCategoryRepository.save(oldCategory);
    }

    @Override
    public void deleteCategory(String id) {
        if (id == null || id.isEmpty()) {
            throw new BadRequestException("Category ID cannot be null or empty");
        }

        ticketCategoryRepository.deleteById(id);

    }

    @Override
    public List<TicketCategory> getAllCategoriesByMatch(String matchId) {
        if (matchId == null || matchId.isEmpty()) {
            throw new BadRequestException("Match ID cannot be null or empty");
        }
        return ticketCategoryRepository.getAllCategoriesByMatchId(matchId);
    }
}
