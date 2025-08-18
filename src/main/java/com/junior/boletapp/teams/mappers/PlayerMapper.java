package com.junior.boletapp.teams.mappers;

import com.junior.boletapp.common.mappers.ImageMapper;
import com.junior.boletapp.teams.controller.request.PlayerRequest;
import com.junior.boletapp.teams.controller.response.PlayerResponse;
import com.junior.boletapp.teams.model.Player;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ImageMapper.class})
public interface PlayerMapper {
    Player toDomain(PlayerRequest player);
    PlayerResponse toResponse(Player player);
}
