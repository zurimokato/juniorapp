package com.junior.boletapp.teams.mappers;

import com.junior.boletapp.common.mappers.ImageMapper;
import com.junior.boletapp.teams.controller.request.TeamRequest;
import com.junior.boletapp.teams.controller.response.SimpleTeamResponse;
import com.junior.boletapp.teams.controller.response.TeamResponse;
import com.junior.boletapp.teams.model.Team;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ImageMapper.class,
        ChampionshipMapper.class,PlayerMapper.class})
public interface TeamMapper {
    TeamResponse toResponse(Team team);
    SimpleTeamResponse toSimpleResponse(Team team);
    Team toEntity(TeamRequest request);
}
