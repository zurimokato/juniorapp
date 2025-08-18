package com.junior.boletapp.matches.controller;

import com.junior.boletapp.matches.controller.api.MatchIncidentApi;
import com.junior.boletapp.matches.controller.request.MatchIncidentRequest;
import com.junior.boletapp.matches.controller.response.MatchIncidentsResponse;
import com.junior.boletapp.matches.mappers.MatchIncidentMapper;
import com.junior.boletapp.matches.service.IMatchIncidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MatchIncidentController implements MatchIncidentApi {
    private final IMatchIncidentService iMatchIncidentService;
    private final MatchIncidentMapper matchIncidentMapper;

    @Override
    public ResponseEntity<MatchIncidentsResponse> addIncident(String matchId, MatchIncidentRequest incidentRequest) {
        MatchIncidentsResponse response = matchIncidentMapper.toResponse(
                iMatchIncidentService.addIncident(matchId, matchIncidentMapper.toModel(incidentRequest))
        );
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<MatchIncidentsResponse>> getIncidentsByMatchId(String matchId) {
        List<MatchIncidentsResponse>responses=iMatchIncidentService.getIncidentsByMatchId(matchId).stream().map(matchIncidentMapper::toResponse).toList();
        return ResponseEntity.ok(responses);
    }

    @Override
    public ResponseEntity<Void> deleteIncident(String matchId, String incidentId) {
        iMatchIncidentService.deleteIncident(matchId, incidentId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<MatchIncidentsResponse> updateIncident(String matchId, String incidentId, MatchIncidentRequest incidentRequest) {
       MatchIncidentsResponse response = matchIncidentMapper.toResponse(
                iMatchIncidentService.updateIncident(matchId, incidentId, matchIncidentMapper.toModel(incidentRequest))
       );
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<MatchIncidentsResponse>> getMatchTimeline(String matchId) {
        List<MatchIncidentsResponse> responses = iMatchIncidentService.getMatchTimeline(matchId)
                .stream()
                .map(matchIncidentMapper::toResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @Override
    public ResponseEntity<MatchIncidentsResponse> getIncidentById(String matchId, String incidentId) {
        MatchIncidentsResponse response = matchIncidentMapper.toResponse(
                iMatchIncidentService.getIncidentById(matchId, incidentId)
        );
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Page<MatchIncidentsResponse>> getIncidentsByMatchIdPaginado(String matchId, Pageable pageable) {
        Page<MatchIncidentsResponse>page= iMatchIncidentService.getIncidents(matchId, pageable).map(matchIncidentMapper::toResponse);
        return ResponseEntity.ok(page);
    }
}
