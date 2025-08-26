package com.junior.boletapp.tickets.controller;

import com.junior.boletapp.tickets.controller.api.TicketCategoryApi;
import com.junior.boletapp.tickets.controller.request.TicketCategoryRequest;
import com.junior.boletapp.tickets.controller.response.TicketCategoryResponse;
import com.junior.boletapp.tickets.mappers.TicketCategoryMapper;
import com.junior.boletapp.tickets.service.ITicketCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TicketCategoryController implements TicketCategoryApi {
    private final ITicketCategoryService ticketCategoryService;
    private final TicketCategoryMapper ticketCategoryMapper;
    @Override
    public ResponseEntity<List<TicketCategoryResponse>> getTicketCategory(String matchId) {
        List<TicketCategoryResponse>responses=ticketCategoryService.getAllCategoriesByMatch(matchId).stream().map(ticketCategoryMapper::toResponse).toList();
        return ResponseEntity.ok(responses);
    }

    @Override
    public ResponseEntity<TicketCategoryResponse> getTicketCategoryById(String categoryId) {
        TicketCategoryResponse response=ticketCategoryMapper.toResponse(ticketCategoryService.getCategoryById(categoryId));

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<TicketCategoryResponse> createTicketCategory(String matchId, TicketCategoryRequest ticketCategoryRequest) {
       TicketCategoryResponse response=ticketCategoryMapper.toResponse(ticketCategoryService.addCategory(ticketCategoryMapper.toModel(ticketCategoryRequest)));
       return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<TicketCategoryResponse> updateTicketCategory(String categoryId, TicketCategoryRequest ticketCategoryRequest) {
        TicketCategoryResponse updatedResponse=ticketCategoryMapper.toResponse(ticketCategoryService.updateCategory(categoryId,ticketCategoryMapper.toModel(ticketCategoryRequest)));
        return ResponseEntity.ok(updatedResponse);
    }

    @Override
    public ResponseEntity<Void> deleteTicketCategory(String categoryId) {
        ticketCategoryService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }
}
