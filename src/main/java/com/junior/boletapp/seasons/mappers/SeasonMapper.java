package com.junior.boletapp.seasons.mappers;


import com.junior.boletapp.seasons.controller.request.SeasonRequest;
import com.junior.boletapp.seasons.controller.response.SeasonResponse;
import com.junior.boletapp.seasons.controller.response.SimpleSeasonResponse;
import com.junior.boletapp.seasons.model.Season;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {TournamentMapper.class})
public interface SeasonMapper {
    SeasonResponse toResponse(Season season);
    Season toModel(SeasonRequest seasonResponse);
    SimpleSeasonResponse toSimpleSeasonResponse(Season season);
}
