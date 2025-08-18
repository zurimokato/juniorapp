package com.junior.boletapp.matches.mappers;

import com.junior.boletapp.common.mappers.ImageMapper;
import com.junior.boletapp.matches.controller.request.MatchRequest;
import com.junior.boletapp.matches.controller.response.MatchResponse;
import com.junior.boletapp.matches.controller.response.SimpleMatchResponse;
import com.junior.boletapp.matches.model.Match;
import com.junior.boletapp.teams.mappers.ChampionshipMapper;
import com.junior.boletapp.teams.mappers.PlayerMapper;
import com.junior.boletapp.teams.mappers.TeamMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        ImageMapper.class,
        TeamMapper.class,
        ChampionshipMapper.class,
        PlayerMapper.class,
        MatchIncidentMapper.class,
})
public interface MatchMapper {
    MatchResponse toResponse(Match match);
    Match toModel(MatchRequest matchRequest);
    SimpleMatchResponse toSimpleResponse(Match match);
}
