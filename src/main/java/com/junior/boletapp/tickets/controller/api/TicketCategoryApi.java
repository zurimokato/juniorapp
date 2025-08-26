package com.junior.boletapp.tickets.controller.api;

import com.junior.boletapp.tickets.controller.request.TicketCategoryRequest;
import com.junior.boletapp.tickets.controller.response.TicketCategoryResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/api/v1")
public interface TicketCategoryApi {
    @GetMapping("/match/{matchId}/ticket-categories")
    ResponseEntity<List<TicketCategoryResponse>> getTicketCategory(@PathVariable("matchId") String matchId);

    @PostMapping("match/{matchId}/ticket-categories")
    ResponseEntity<TicketCategoryResponse> createTicketCategory(@PathVariable("matchId") String matchId, @RequestBody TicketCategoryRequest ticketCategoryRequest);

    @PutMapping("/ticket-categories/{categoryId}")
    ResponseEntity<TicketCategoryResponse> updateTicketCategory(@PathVariable("categoryId") String categoryId, @RequestBody TicketCategoryRequest ticketCategoryRequest);

    @DeleteMapping("/ticket-categories/{categoryId}")
    ResponseEntity<Void> deleteTicketCategory(@PathVariable("categoryId") String categoryId);

}
