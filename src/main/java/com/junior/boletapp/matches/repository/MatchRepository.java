package com.junior.boletapp.matches.repository;

import com.junior.boletapp.matches.model.Match;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MatchRepository extends MongoRepository<Match, String> {
    List<Match> findAllByHomeTeam_IdAndAwayTeam_Id(String homeTeamId, String awayTeamId);
    boolean existsByHomeTeam_IdAndAwayTeam_IdAndDate(String homeTeam, String awayTeam, LocalDate date);
}
