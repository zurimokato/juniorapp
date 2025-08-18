package com.junior.boletapp.seasons.controller.api;


import com.junior.boletapp.seasons.controller.request.SeasonRequest;
import com.junior.boletapp.seasons.controller.response.SeasonResponse;
import com.junior.boletapp.seasons.controller.response.SimpleSeasonResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Seasons")
public interface SeasonApi {

    @GetMapping
    ResponseEntity<List<SeasonResponse>> getSeasons();

    @GetMapping("/paginated")
    ResponseEntity<Page<SimpleSeasonResponse>> getSeasons(Pageable pageable);

    @GetMapping("/{id}")
    ResponseEntity<SeasonResponse> getSeasonById(@PathVariable String id);

    @PostMapping
    ResponseEntity<SeasonResponse> createSeason(@RequestBody SeasonRequest seasonRequest);

    @PutMapping("/{id}")
    ResponseEntity<SeasonResponse> updateSeason(@PathVariable String id, @RequestBody SeasonRequest seasonRequest);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteSeason(@PathVariable String id);



}
