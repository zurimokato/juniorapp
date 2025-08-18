package com.junior.boletapp.seasons.controller;

import com.junior.boletapp.seasons.controller.api.SeasonApi;
import com.junior.boletapp.seasons.controller.request.SeasonRequest;
import com.junior.boletapp.seasons.controller.response.SeasonResponse;
import com.junior.boletapp.seasons.controller.response.SimpleSeasonResponse;
import com.junior.boletapp.seasons.mappers.SeasonMapper;
import com.junior.boletapp.seasons.service.ISeasonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/seasons")
public class SeasonController implements SeasonApi {

    private final ISeasonService seasonService;
    private final SeasonMapper seasonMapper;

    @Override
    public ResponseEntity<List<SeasonResponse>> getSeasons() {

        return ResponseEntity.ok(seasonService.getAllSeasons().stream().map(seasonMapper::toResponse).toList());
    }

    @Override
    public ResponseEntity<Page<SimpleSeasonResponse>> getSeasons(Pageable pageable) {
        Page<SimpleSeasonResponse> seasonResponses = seasonService.getSeasons(pageable)
                .map(seasonMapper::toSimpleSeasonResponse);
        return ResponseEntity.ok(seasonResponses);
    }

    @Override
    public ResponseEntity<SeasonResponse> getSeasonById(String id) {
        if(id==null || id.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        SeasonResponse seasonResponse = seasonMapper.toResponse(seasonService.getSeasonById(id));
        return ResponseEntity.ok(seasonResponse);
    }

    @Override
    public ResponseEntity<SeasonResponse> createSeason(SeasonRequest seasonRequest) {
        if (seasonRequest == null) {
            return ResponseEntity.badRequest().build();
        }
        var season = seasonMapper.toModel(seasonRequest);
        var savedSeason = seasonService.createSeason(season);
        return ResponseEntity.ok(seasonMapper.toResponse(savedSeason));
    }

    @Override
    public ResponseEntity<SeasonResponse> updateSeason(String id, SeasonRequest seasonRequest) {
        if (seasonRequest == null) {
            return ResponseEntity.badRequest().build();
        }
        var season = seasonMapper.toModel(seasonRequest);
        var updatedSeason = seasonService.updateSeason(id, season);
        return ResponseEntity.ok(seasonMapper.toResponse(updatedSeason));
    }

    @Override
    public ResponseEntity<Void> deleteSeason(String id) {
        if(id==null || id.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        seasonService.deleteSeason(id);
        return ResponseEntity.noContent().build();
    }
}
