package com.junior.boletapp.teams.controller.request;

import com.junior.boletapp.common.constants.ChampionshipTypeEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChampionshipRequest {
    private String id;
    private String name;
    private String year;
    private String description;
    private ChampionshipTypeEnum type;

}
