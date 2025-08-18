package com.junior.boletapp.matches.repository;

import com.junior.boletapp.matches.model.MatchIncident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatchIncidentRepository extends MongoRepository<MatchIncident, String> {

    Optional<MatchIncident>findMatchIncidentByIdAndMatchId(String id, String matchId);
    List<MatchIncident>findMatchIncidentByMatchId(String matchId);
    Page<MatchIncident> findMatchIncidentByMatchId(String matchId, Pageable pageable);

    List<MatchIncident> findMatchIncidentByMatchIdOrderByMinuteDesc(String matchId);
}
