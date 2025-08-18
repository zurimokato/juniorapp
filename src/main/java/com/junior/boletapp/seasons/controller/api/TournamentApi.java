package com.junior.boletapp.seasons.controller.api;

import com.junior.boletapp.seasons.controller.request.TournamentRequest;
import com.junior.boletapp.seasons.controller.response.SimpleTournamentResponse;
import com.junior.boletapp.seasons.controller.response.TournamentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

public interface TournamentApi {
    // Define methods for tournament-related operations here
    // For example:

    @PostMapping
    ResponseEntity<TournamentResponse> createTournament(@RequestBody TournamentRequest request) throws URISyntaxException;

    @GetMapping("/{id}")
    ResponseEntity<TournamentResponse> getTournamentById(@PathVariable String id);

    @GetMapping
    ResponseEntity<List<TournamentResponse>> getAllTournaments();

    @PutMapping("/{id}")
    ResponseEntity<TournamentResponse> updateTournament(@PathVariable String id, @RequestBody TournamentRequest request);

    @DeleteMapping("/{seasonId}/{id}")
    ResponseEntity<Void> deleteTournament(@PathVariable String seasonId,@PathVariable String id);

    @GetMapping("/{seasonId}/by-season")
    ResponseEntity<List<SimpleTournamentResponse>> getTournamentsBySeasonId(@PathVariable String seasonId);
    @GetMapping("/{seasonId}/paginated")
    ResponseEntity<Page<SimpleTournamentResponse>>getTournamentsBySeasonId(@PathVariable String seasonId, Pageable pageable);
}
