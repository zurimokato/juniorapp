package com.junior.boletapp.seasons.service.impl;

import com.junior.boletapp.app.exceptions.BadRequestException;
import com.junior.boletapp.app.exceptions.NotFoundException;
import com.junior.boletapp.common.service.ICommonImageService;
import com.junior.boletapp.matches.model.Match;
import com.junior.boletapp.matches.service.IMatchService;
import com.junior.boletapp.seasons.model.Season;
import com.junior.boletapp.seasons.model.Tournament;
import com.junior.boletapp.seasons.repository.SeasonRepository;
import com.junior.boletapp.seasons.repository.TournamentRepository;
import com.junior.boletapp.seasons.service.ITournament;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TournamentService implements ITournament {
    private final TournamentRepository tournamentRepository;
    private final SeasonRepository seasonRepository;
    private final ICommonImageService imageService;
    private final IMatchService matchService;

    @Override
    public Tournament getTournamentById(String id) {
        if (id == null || id.isEmpty()) {
            throw new BadRequestException("Tournament ID cannot be null or empty");
        }
        return tournamentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tournament not found with ID: " + id));
    }


    @Override
    public Tournament addTournament(String seasonId, Tournament tournament) {
        if (seasonId == null || seasonId.isEmpty()) {
            throw new BadRequestException("Season ID cannot be null or empty");
        }
        Season season = seasonRepository.findById(seasonId)
                .orElseThrow(()->new NotFoundException("Season not found with ID: " + seasonId));
        if (tournament.getLogo() != null) {
            tournament.setLogo(imageService.saveImage(tournament.getLogo()));
        }
        if (tournament.getMatches() != null && !tournament.getMatches().isEmpty()) {
            HashSet<Match> matches = new HashSet<>();
            tournament.getMatches().forEach(match -> matches.add(matchService.addMatch(match)));
            tournament.setMatches(matches);
        }
        tournament.setSeasonId(seasonId);

        Tournament savedTournament = tournamentRepository.save(tournament);
        if (season.getTournaments() == null) {
            season.setTournaments(new ArrayList<>());
        }
        season.getTournaments().add(savedTournament);

        return savedTournament;
    }

    @Override
    public Tournament updateTournament(String seasonId, String id, Tournament tournament) {
        if (seasonId == null || seasonId.isEmpty()) {
            throw new BadRequestException("Season ID cannot be null or empty");
        }
        if (id == null || id.isEmpty()) {
            throw new BadRequestException("Tournament ID cannot be null or empty");
        }
        Tournament existingTournament = getTournamentById(id);
        existingTournament.setName(tournament.getName());
        existingTournament.setStartDate(tournament.getStartDate());
        existingTournament.setEndDate(tournament.getEndDate());
        if (tournament.getLogo() != null) {
            if (existingTournament.getLogo() != null) {
                imageService.deleteImageById(existingTournament.getLogo().getId());
            }
            existingTournament.setLogo(imageService.saveImage(tournament.getLogo()));
        }
        if (tournament.getMatches() != null && !tournament.getMatches().isEmpty()) {
            HashSet<Match> matches = new HashSet<>();
            tournament.getMatches().forEach(match -> matches.add(matchService.addMatch(match)));
            existingTournament.setMatches(matches);
        }
        return tournamentRepository.save(existingTournament);
    }

    @Override
    public void deleteTournament(String seasonId, String id) {
        if (seasonId == null || seasonId.isEmpty() || id == null || id.isEmpty()) {
            throw new BadRequestException("Season ID and Tournament ID cannot be null or empty");
        }
        Tournament tournament = getTournamentById(id);
        tournamentRepository.delete(tournament);

    }

    @Override
    public List<Tournament> getTournamentsBySeason(String seasonId) {
        if (seasonId == null || seasonId.isEmpty()) {
            throw new BadRequestException("Season ID cannot be null or empty");
        }
        Season season = seasonRepository.findById(seasonId).orElseThrow(()-> new NotFoundException("Season not found with ID: " + seasonId));

        return tournamentRepository.findBySeasonId(season.getId());
    }

    @Override
    public Page<Tournament> getTournaments(String seasonId, Pageable pageable) {
        return tournamentRepository.findAllBySeasonId(seasonId,pageable);
    }

    @Override
    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }
}
