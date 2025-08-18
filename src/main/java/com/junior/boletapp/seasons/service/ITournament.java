package com.junior.boletapp.seasons.service;

import com.junior.boletapp.seasons.model.Tournament;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITournament {
    Tournament getTournamentById(String id);
    Tournament addTournament(String seasonId, Tournament tournament);
    Tournament updateTournament(String seasonId, String id, Tournament tournament);
    void deleteTournament(String seasonId, String id);
    List<Tournament> getTournamentsBySeason(String seasonId);
    Page<Tournament> getTournaments(String seasonId, Pageable pageable);

    List<Tournament> getAllTournaments();
}
