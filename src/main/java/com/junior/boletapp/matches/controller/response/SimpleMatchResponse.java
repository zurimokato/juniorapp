package com.junior.boletapp.matches.controller.response;

import com.junior.boletapp.common.dtos.ImageDto;
import com.junior.boletapp.matches.constants.MatchStatusEnum;
import com.junior.boletapp.teams.controller.response.SimpleTeamResponse;
import com.junior.boletapp.teams.controller.response.TeamResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
public class SimpleMatchResponse {

    private String id;
    private SimpleTeamResponse homeTeam;
    private SimpleTeamResponse awayTeam;
    private LocalDate date;
    private LocalTime startTime;
    private String location; // e.g., "Stadium Name, City"
    private MatchStatusEnum status; // e.g., "scheduled", "in_progress", "completed"
    private String competition; // e.g., "League Name", "Cup Name"
    private ImageDto banner;
}
