package com.junior.boletapp.seasons.service.impl;

import com.junior.boletapp.app.exceptions.BadRequestException;
import com.junior.boletapp.app.exceptions.NotFoundException;
import com.junior.boletapp.seasons.model.Season;
import com.junior.boletapp.seasons.model.Tournament;
import com.junior.boletapp.seasons.repository.SeasonRepository;
import com.junior.boletapp.seasons.service.ISeasonService;
import com.junior.boletapp.seasons.service.ITournament;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeasonService implements ISeasonService {
    private final SeasonRepository seasonRepository;
    private final ITournament tournamentService;

    @Override
    public Season createSeason(Season season) {
        if (season == null) {
            throw new BadRequestException("Season cannot be null");
        }
        if (season.getTournaments() != null) {
            List<Tournament> tournaments = new ArrayList<>();
            for (Tournament tournament : season.getTournaments()) {
                Tournament savedTournament = tournamentService.addTournament(season.getId(), tournament);
                tournaments.add(savedTournament);
            }
            season.setTournaments(tournaments);


        }
        return seasonRepository.save(season);
    }

    @Override
    public Season updateSeason(String id, Season season) {
        if (id == null || season == null) {
            throw new BadRequestException("Season ID and season cannot be null");
        }
        Season existingSeason = seasonRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Season not found with ID: " + id));
        existingSeason.setName(season.getName());
        existingSeason.setStartDate(season.getStartDate());
        existingSeason.setEndDate(season.getEndDate());
        if (season.getTournaments() != null) {
            List<Tournament> updatedTournaments = new ArrayList<>();
            for (Tournament tournament : season.getTournaments()) {
                Tournament updatedTournament = tournamentService.updateTournament(id, tournament.getId(), tournament);
                updatedTournaments.add(updatedTournament);
            }
            existingSeason.setTournaments(updatedTournaments);

        }
        return seasonRepository.save(existingSeason);
    }

    @Override
    public void deleteSeason(String id) {
        if (id == null || id.isEmpty()) {
            throw new BadRequestException("Season ID cannot be null or empty");
        }
        seasonRepository.deleteById(id);

    }

    @Override
    public List<Season> getAllSeasons() {
        return seasonRepository.findAll();
    }

    @Override
    public Season getSeasonById(String id) {
        return seasonRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Season not found with ID: " + id));
    }

    @Override
    public Page<Season> getSeasons(Pageable pageable) {

        return seasonRepository.findAll(pageable);
    }
}
