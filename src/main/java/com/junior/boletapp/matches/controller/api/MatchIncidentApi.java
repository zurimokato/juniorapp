package com.junior.boletapp.matches.controller.api;

import com.junior.boletapp.matches.controller.request.MatchIncidentRequest;
import com.junior.boletapp.matches.controller.response.MatchIncidentsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/matches/{matchId}/incidents")
public interface MatchIncidentApi {
    // Define methods for handling match incidents here
    // For example:
    @PostMapping
    ResponseEntity<MatchIncidentsResponse> addIncident(@PathVariable String matchId, @RequestBody MatchIncidentRequest incidentRequest);
    @GetMapping
    ResponseEntity<List<MatchIncidentsResponse>> getIncidentsByMatchId(@PathVariable String matchId);
    @DeleteMapping("/{incidentId}")
    ResponseEntity<Void> deleteIncident(@PathVariable String matchId, @PathVariable String incidentId);
    @PutMapping("/{incidentId}")
    ResponseEntity<MatchIncidentsResponse> updateIncident(@PathVariable String matchId, @PathVariable String incidentId, @RequestBody MatchIncidentRequest incidentRequest);
    @GetMapping("/timeline")
    ResponseEntity<List<MatchIncidentsResponse>> getMatchTimeline(@PathVariable String matchId);
    @GetMapping("/{incidentId}")
    ResponseEntity<MatchIncidentsResponse> getIncidentById(@PathVariable String matchId, @PathVariable String incidentId);
    @GetMapping("/paginado")
    ResponseEntity<Page<MatchIncidentsResponse>> getIncidentsByMatchIdPaginado(@PathVariable String matchId, Pageable pageable);

}
