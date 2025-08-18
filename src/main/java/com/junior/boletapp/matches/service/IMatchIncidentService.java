package com.junior.boletapp.matches.service;


import com.junior.boletapp.matches.model.MatchIncident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IMatchIncidentService {

    MatchIncident addIncident(String matchId,MatchIncident incident);

    MatchIncident getIncidentById(String matchId,String id);

    MatchIncident updateIncident(String matchId,String id, MatchIncident incident);

    void deleteIncident(String matchId,String id);

    List<MatchIncident> getIncidentsByMatchId(String matchId);

    Page<MatchIncident> getIncidents(String matchId,Pageable pageable);

    List<MatchIncident> getMatchTimeline(String matchId);
}
