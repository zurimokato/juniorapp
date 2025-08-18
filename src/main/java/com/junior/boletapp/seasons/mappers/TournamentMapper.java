package com.junior.boletapp.seasons.mappers;

import com.junior.boletapp.common.mappers.ImageMapper;
import com.junior.boletapp.matches.mappers.MatchMapper;
import com.junior.boletapp.seasons.controller.request.TournamentRequest;
import com.junior.boletapp.seasons.controller.response.SimpleTournamentResponse;
import com.junior.boletapp.seasons.controller.response.TournamentResponse;
import com.junior.boletapp.seasons.model.Tournament;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {
                // Add other mappers here if needed
                ImageMapper.class,
                MatchMapper.class,
        })
public interface TournamentMapper {
    Tournament toModel(TournamentRequest request);

    TournamentResponse toResponse(Tournament tournament);
    SimpleTournamentResponse toSimpleTournamentResponse(Tournament tournament);
}
