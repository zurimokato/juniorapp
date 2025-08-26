package com.junior.boletapp.tickets.controller.api;


import com.junior.boletapp.tickets.controller.request.TicketRequest;
import com.junior.boletapp.tickets.controller.response.TicketResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/tickets")
public interface TicketApi {
    @PostMapping("{categoryId}")
    ResponseEntity<TicketResponse> createTicket(@PathVariable(name = "categoryId")String categoryID, @RequestBody TicketRequest ticket);

    @GetMapping("{id}")
    ResponseEntity<TicketResponse> getTicketById(@PathVariable("id") String id);

    @GetMapping("list/{categoryId}")
    ResponseEntity<List<TicketResponse>> getTicketsByCategory(@PathVariable(name ="categoryId") String categoryId);


    @PutMapping
    ResponseEntity<TicketResponse> updateTicket(@RequestBody TicketRequest ticket);

    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteTicket(@PathVariable(name = "id") String id);

    @GetMapping("count/{categoryId}/sold")
    ResponseEntity<Integer> getNumberOfTicketsByCategorySold(@PathVariable(name = "categoryId") String categoryId);


}
