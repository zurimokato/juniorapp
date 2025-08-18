package com.junior.boletapp.teams.mappers;

import com.junior.boletapp.teams.controller.request.ChampionshipRequest;
import com.junior.boletapp.teams.controller.response.ChampionshipResponse;
import com.junior.boletapp.teams.model.Championship;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChampionshipMapper {

    Championship toDomain(ChampionshipRequest championship);
    ChampionshipResponse toResponse(Championship championship);
}
