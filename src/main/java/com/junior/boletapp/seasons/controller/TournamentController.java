package com.junior.boletapp.seasons.controller;

import com.junior.boletapp.seasons.controller.api.TournamentApi;
import com.junior.boletapp.seasons.controller.request.TournamentRequest;
import com.junior.boletapp.seasons.controller.response.SimpleTournamentResponse;
import com.junior.boletapp.seasons.controller.response.TournamentResponse;
import com.junior.boletapp.seasons.mappers.TournamentMapper;
import com.junior.boletapp.seasons.service.ITournament;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/tournaments")
public class TournamentController implements TournamentApi {
    private final ITournament tournamentService;
    private final TournamentMapper tournamentMapper;
    @Override
    public ResponseEntity<TournamentResponse> createTournament(TournamentRequest request) throws URISyntaxException {
        if (request == null) {
            return ResponseEntity.badRequest().build();
        }
        var tournament = tournamentService.addTournament(request.getSeasonId(), tournamentMapper.toModel(request));
        return  ResponseEntity.created(new URI("tournament")).body(tournamentMapper.toResponse(tournament));

    }

    @Override
    public ResponseEntity<TournamentResponse> getTournamentById(String id) {
        if (id == null || id.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var tournament = tournamentService.getTournamentById(id);
        return ResponseEntity.ok(tournamentMapper.toResponse(tournament));
    }

    @Override
    public ResponseEntity<List<TournamentResponse>> getAllTournaments() {
        List<TournamentResponse> tournaments = tournamentService.getAllTournaments()
                .stream()
                .map(tournamentMapper::toResponse)
                .toList();
        return ResponseEntity.ok(tournaments);
    }

    @Override
    public ResponseEntity<TournamentResponse> updateTournament(String id, TournamentRequest request) {
        if (id == null || id.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var tournament = tournamentService.updateTournament(request.getSeasonId(), id, tournamentMapper.toModel(request));

        return ResponseEntity.ok(tournamentMapper.toResponse(tournament));
    }

    @Override
    public ResponseEntity<Void> deleteTournament(String seasonId,String id) {
        if (id == null || id.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        tournamentService.deleteTournament(seasonId,id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<SimpleTournamentResponse>> getTournamentsBySeasonId(String seasonId) {
        List<SimpleTournamentResponse>responses= tournamentService.getTournamentsBySeason(seasonId)
                .stream()
                .map(tournamentMapper::toSimpleTournamentResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @Override
    public ResponseEntity<Page<SimpleTournamentResponse>> getTournamentsBySeasonId(String seasonId, Pageable pageable) {
        Page<SimpleTournamentResponse>page= tournamentService.getTournaments(seasonId, pageable)
                .map(tournamentMapper::toSimpleTournamentResponse);
        return ResponseEntity.ok(page);
    }
}
