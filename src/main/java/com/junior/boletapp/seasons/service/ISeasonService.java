package com.junior.boletapp.seasons.service;

import com.junior.boletapp.seasons.model.Season;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ISeasonService {
    // Define methods for season management, e.g., create, update, delete, get seasons
    // Example:
    Season createSeason(Season season);

    Season updateSeason(String id, Season season);

    void deleteSeason(String id);

    List<Season> getAllSeasons();

    Season getSeasonById(String id);

    Page<Season> getSeasons(Pageable pageable);
}
