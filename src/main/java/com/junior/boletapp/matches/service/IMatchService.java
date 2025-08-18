package com.junior.boletapp.matches.service;

import com.junior.boletapp.matches.model.Match;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IMatchService {
    Match addMatch(Match match);
    Match getMatchById(String id);
    Match updateMatch(String id, Match match);
    Match updateScore(String id, String score);
    void deleteMatch(String id);
    List<Match> getMatchByTeams(String team1, String team2);
    Page<Match> getMatches(Pageable pageable);
}
