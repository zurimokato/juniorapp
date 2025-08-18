package com.junior.boletapp.matches.service.impl;

import com.junior.boletapp.app.exceptions.BadRequestException;
import com.junior.boletapp.app.exceptions.NotFoundException;
import com.junior.boletapp.matches.constants.MatchIncidentTypeEnum;
import com.junior.boletapp.matches.constants.MatchStatusEnum;
import com.junior.boletapp.matches.model.Match;
import com.junior.boletapp.matches.model.MatchIncident;
import com.junior.boletapp.matches.repository.MatchIncidentRepository;
import com.junior.boletapp.matches.service.IMatchIncidentService;
import com.junior.boletapp.matches.service.IMatchService;
import com.junior.boletapp.matches.validation.MatchIncidentValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchIncidentService implements IMatchIncidentService {
    public static final String INCIDENT_NOT_FOUND_WITH_ID = "Incident not found with ID: ";
    private final MatchIncidentRepository repository;
    private final IMatchService matchService;
    private final MatchIncidentValidator matchIncidentValidator;


    @Override
    public MatchIncident addIncident(String matchId, MatchIncident incident) {
        try {
            matchIncidentValidator.validateIncident(incident, matchId);

            var match = matchService.getMatchById(matchId);
            matchIncidentValidator.validateIncident(match, incident);

            if (match.getIncidents() == null) {
                match.setIncidents(new ArrayList<>());
            }
            MatchIncident savedIncident = repository.save(incident);
            match.getIncidents().add(savedIncident);

            return savedIncident;

        }catch (Exception e) {
            throw new BadRequestException("Error adding incident: " + e.getMessage());
        }

    }

    @Override
    public MatchIncident getIncidentById(String matchId, String id) {
        if (matchId == null || matchId.isEmpty() || id == null || id.isEmpty()) {
            throw new BadRequestException("Match ID and Incident ID cannot be null or empty");
        }
        return repository.findMatchIncidentByIdAndMatchId(id, matchId)
                .orElseThrow(() -> new NotFoundException(INCIDENT_NOT_FOUND_WITH_ID + id));
    }

    @Override
    public MatchIncident updateIncident(String matchId, String id, MatchIncident incident) {
        if (matchId == null || matchId.isEmpty() || id == null || id.isEmpty()) {
            throw new BadRequestException("Match ID and Incident ID cannot be null or empty");
        }
        Match match = matchService.getMatchById(matchId);
        if (match.getStatus() != MatchStatusEnum.ONGOING) {
            throw new BadRequestException("Cannot add or update incidents to a match that is not ongoing");
        }
        MatchIncident existingIncident = repository.findMatchIncidentByIdAndMatchId(id, matchId)
                .orElseThrow(() -> new NotFoundException(INCIDENT_NOT_FOUND_WITH_ID + id));
        existingIncident.setId(incident.getId());
        existingIncident.setDetail(incident.getDetail());
        existingIncident.setType(incident.getType());
        existingIncident.setMinute(incident.getMinute());
        existingIncident.setLocalTeam(incident.isLocalTeam());
        existingIncident.setScore(incident.getScore());
        if (incident.getType() == MatchIncidentTypeEnum.SUBSTITUTION) {
            existingIncident.setPlayerIn(incident.getPlayerIn());
            existingIncident.setPlayerOut(incident.getPlayerOut());
        } else {
            existingIncident.setPlayerName(incident.getPlayerName());
        }
        MatchIncident updatedIncident = repository.save(existingIncident);
        if (match.getIncidents() == null) {
            match.setIncidents(new ArrayList<>());
        }
        match.getIncidents().add(updatedIncident);
        matchService.updateMatch(matchId, match);
        return updatedIncident;


    }

    @Override
    public void deleteIncident(String matchId, String id) {
        if (matchId == null || matchId.isEmpty() || id == null || id.isEmpty()) {
            throw new BadRequestException("Match ID and Incident ID cannot be null or empty");
        }
        Match match = matchService.getMatchById(matchId);
        if (match == null) {
            throw new NotFoundException("Match not found with ID: " + matchId);
        }
        MatchIncident existingIncident = repository.findMatchIncidentByIdAndMatchId(id, matchId)
                .orElseThrow(() -> new NotFoundException(INCIDENT_NOT_FOUND_WITH_ID + id));
        repository.delete(existingIncident);

    }

    @Override
    public List<MatchIncident> getIncidentsByMatchId(String matchId) {
        if (matchId == null || matchId.isEmpty()) {
            throw new BadRequestException("Match ID cannot be null or empty");
        }
        Match match = matchService.getMatchById(matchId);
        if (match == null) {
            throw new NotFoundException("Match not found with ID: " + matchId);
        }


        return repository.findMatchIncidentByMatchId(matchId);
    }

    @Override
    public Page<MatchIncident> getIncidents(String matchId, Pageable pageable) {
        if (matchId == null || matchId.isEmpty()) {
            throw new BadRequestException("Match ID cannot be null or empty");
        }
        Match match = matchService.getMatchById(matchId);
        if (match == null) {
            throw new NotFoundException("Match not found with ID: " + matchId);
        }
        return repository.findMatchIncidentByMatchId(matchId, pageable);
    }

    @Override
    public List<MatchIncident> getMatchTimeline(String matchId) {
        if (matchId == null || matchId.isEmpty()) {
            throw new BadRequestException("Match ID cannot be null or empty");
        }
        Match match = matchService.getMatchById(matchId);
        if (match == null) {
            throw new NotFoundException("Match not found with ID: " + matchId);
        }

        return repository.findMatchIncidentByMatchIdOrderByMinuteDesc(matchId);
    }
}
