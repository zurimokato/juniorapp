package com.junior.boletapp.seasons.controller.response;


import com.junior.boletapp.common.dtos.ImageDto;
import com.junior.boletapp.matches.constants.MatchTypeEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SimpleTournamentResponse {

    private String id;
    private String name;
    private String description;
    private ImageDto logo; // URL to the tournament logo or image
    private String startDate; // e.g., "2023-01-01"
    private String endDate; // e.g., "2023-12-31"
    private MatchTypeEnum tournamentType; // e.g., "league", "knockout", "friendly"
    private String seasonId;
}
