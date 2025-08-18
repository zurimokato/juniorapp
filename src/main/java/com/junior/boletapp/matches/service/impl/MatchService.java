package com.junior.boletapp.matches.service.impl;

import com.junior.boletapp.app.exceptions.AppException;
import com.junior.boletapp.app.exceptions.BadRequestException;
import com.junior.boletapp.app.exceptions.NotFoundException;
import com.junior.boletapp.common.models.Image;
import com.junior.boletapp.common.service.impl.ImageService;
import com.junior.boletapp.matches.constants.MatchStatusEnum;
import com.junior.boletapp.matches.model.Match;
import com.junior.boletapp.matches.repository.MatchRepository;
import com.junior.boletapp.matches.service.IMatchService;
import com.junior.boletapp.matches.validation.MatchValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatchService implements IMatchService {
    public static final String MATCH_NOT_FOUND_WITH_ID = "Match not found with ID:";
    private final MatchRepository matchRepository;
    private final ImageService imageService;
    private final MatchValidator matchValidator;

    @Override
    public Match addMatch(Match match) {
        matchValidator.validate(match);
        if(matchRepository.existsByHomeTeam_IdAndAwayTeam_IdAndDate(
                match.getHomeTeam().getId(),
                match.getAwayTeam().getId(),
                match.getDate())) {
            throw new IllegalArgumentException("Match already exists for the given teams and date");
        }
        addImages(match);
        return matchRepository.save(match);
    }

    private void addImages(Match match) {
        if (match.getBanner() != null) {
            match.setBanner(imageService.saveImage(match.getBanner()));
        }
        if (match.getImages() != null) {
            List<Image> images = new ArrayList<>();
            match.getImages().forEach(image -> {
                if (image != null) {
                    images.add(imageService.saveImage(image));
                }
            });
            match.setImages(images);
        }
    }

    @Override
    public Match getMatchById(String id) {
        if (id == null || id.isEmpty()) {
            throw new BadRequestException("Match ID cannot be null or empty");
        }
        return matchRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MATCH_NOT_FOUND_WITH_ID + " " + id));
    }

    @Override
    public Match updateMatch(String id, Match match) {
        matchValidator.validateMatchUpdate(id, match);
        validateInputs(id, match);
        Match existingMatch = matchRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MATCH_NOT_FOUND_WITH_ID + " " + id));
        updateBasicFields(existingMatch, match);
        updateStartTime(existingMatch, match);
        matchValidator.validatePostponedTransition(existingMatch, match);

        return matchRepository.save(existingMatch);

    }

    private void validateInputs(String id, Match match) {
        if (id == null || id.isEmpty()) {
            throw new BadRequestException("Match ID cannot be null or empty");
        }
        if (match == null) {
            throw new BadRequestException("Match cannot be null");
        }
    }

    private void updateBasicFields(Match existing, Match incoming) {
        Optional.ofNullable(incoming.getHomeTeam()).ifPresent(existing::setHomeTeam);
        Optional.ofNullable(incoming.getAwayTeam()).ifPresent(existing::setAwayTeam);
        Optional.ofNullable(incoming.getStatus()).ifPresent(existing::setStatus);
        Optional.ofNullable(incoming.getDate()).ifPresent(existing::setDate);
        Optional.ofNullable(incoming.getScore()).ifPresent(existing::setScore);
        Optional.ofNullable(incoming.getLocation()).ifPresent(existing::setLocation);
        Optional.ofNullable(incoming.getReferee()).ifPresent(existing::setReferee);
        Optional.ofNullable(incoming.getCompetition()).ifPresent(existing::setCompetition);

        // Puedes conservar el que ya tenías
        addImages(incoming);
    }

    private void updateStartTime(Match existing, Match incoming) {
        if (incoming.getStartTime() == null) return;

        if (existing.getStatus() == MatchStatusEnum.SCHEDULED) {
            existing.setStartTime(incoming.getStartTime());
        } else {
            throw new AppException("Cannot set start time for a match that is not scheduled");
        }
    }

    @Override
    public Match updateScore(String id, String score) {


        if (id == null || id.isEmpty()) {
            throw new BadRequestException("Match ID cannot be null or empty");
        }
        if (score == null) {
            throw new BadRequestException("Scores cannot be null");
        }
        Match match = matchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(MATCH_NOT_FOUND_WITH_ID + " " + id));
        if (match.getStatus() != MatchStatusEnum.FINISHED) {
            match.setScore(score);
            matchRepository.save(match);
            return match;
        }
        throw new AppException("Cannot update score for a match that has already finished");

    }

    @Override
    public void deleteMatch(String id) {
        if (id == null || id.isEmpty()) {
            throw new BadRequestException("Match ID cannot be null or empty");
        }
        Match match = matchRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MATCH_NOT_FOUND_WITH_ID + " " + id));
        matchRepository.delete(match);

    }

    @Override
    public List<Match> getMatchByTeams(String team1, String team2) {
        if (team1 == null || team2 == null) {
            throw new BadRequestException("Team names cannot be null");
        }
        if (team1.isEmpty() || team2.isEmpty()) {
            throw new BadRequestException("Team names cannot be empty");
        }
        return matchRepository.findAllByHomeTeam_IdAndAwayTeam_Id(team1, team2);
    }

    @Override
    public Page<Match> getMatches(Pageable pageable) {
        return matchRepository.findAll(pageable);
    }
}
