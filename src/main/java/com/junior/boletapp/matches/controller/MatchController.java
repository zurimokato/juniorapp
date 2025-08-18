package com.junior.boletapp.matches.controller;

import com.junior.boletapp.matches.controller.api.MatchApi;
import com.junior.boletapp.matches.controller.request.MatchRequest;
import com.junior.boletapp.matches.controller.request.UpdateScoreRequest;
import com.junior.boletapp.matches.controller.response.MatchResponse;
import com.junior.boletapp.matches.controller.response.SimpleMatchResponse;
import com.junior.boletapp.matches.mappers.MatchMapper;
import com.junior.boletapp.matches.service.IMatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MatchController implements MatchApi {
    private final IMatchService matchService;
    private final MatchMapper matchMapper;
    @Override
    public ResponseEntity<MatchResponse> addMatch(MatchRequest matchRequest) {
        if (matchRequest == null) {
            return ResponseEntity.badRequest().build();
        }
        var match = matchMapper.toModel(matchRequest);
        var savedMatch = matchService.addMatch(match);
        return ResponseEntity.ok(matchMapper.toResponse(savedMatch));
    }

    @Override
    public ResponseEntity<PagedModel<EntityModel<SimpleMatchResponse>>> getMatches(Pageable pageable , PagedResourcesAssembler<SimpleMatchResponse> assembler) {
        Page<SimpleMatchResponse> matchResponses = matchService.getMatches(pageable)
                .map(matchMapper::toSimpleResponse);
        return ResponseEntity.ok(assembler.toModel(matchResponses));
    }

    @Override
    public ResponseEntity<MatchResponse> getMatchById(String id) {
        if (id == null || id.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var match = matchService.getMatchById(id);

        return match != null
                ? ResponseEntity.ok(matchMapper.toResponse(match))
                : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<MatchResponse>> getMatchesByTeams(String team1, String team2) {
        if (team1 == null || team2 == null || team1.isEmpty() || team2.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<MatchResponse> matchResponses = matchService.getMatchByTeams(team1, team2)
                .stream()
                .map(matchMapper::toResponse)
                .toList();
        return matchResponses.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(matchResponses);
    }


    @Override
    public ResponseEntity<MatchResponse> updateMatch(String id, MatchRequest matchRequest) {
        if (id == null || id.isEmpty() || matchRequest == null) {
            return ResponseEntity.badRequest().build();
        }
        var match = matchMapper.toModel(matchRequest);
        var updatedMatch = matchService.updateMatch(id, match);
        return updatedMatch != null
                ? ResponseEntity.ok(matchMapper.toResponse(updatedMatch))
                : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<MatchResponse> updateMatchScore(String id, UpdateScoreRequest updateScoreRequest) {
        if (id == null || id.isEmpty() || updateScoreRequest == null) {
            return ResponseEntity.badRequest().build();
        }
        var updatedMatch = matchService.updateScore(id, updateScoreRequest.getScore());

        return updatedMatch != null
                ? ResponseEntity.ok(matchMapper.toResponse(updatedMatch))
                : ResponseEntity.notFound().build();

    }

    @Override
    public ResponseEntity<Void> deleteMatch(String id) {
    if (id == null || id.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        matchService.deleteMatch(id);
        return ResponseEntity.noContent().build();
    }
}
