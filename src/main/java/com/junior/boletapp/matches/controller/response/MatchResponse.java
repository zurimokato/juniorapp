package com.junior.boletapp.matches.controller.response;

import com.junior.boletapp.common.dtos.ImageDto;
import com.junior.boletapp.matches.constants.MatchStatusEnum;
import com.junior.boletapp.matches.constants.MatchTypeEnum;
import com.junior.boletapp.teams.controller.response.SimpleTeamResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Builder
public class MatchResponse {
    private String id;
    private SimpleTeamResponse homeTeam;
    private SimpleTeamResponse awayTeam;
    private LocalDate date;
    private LocalTime startTime;
    private String location; // e.g., "Stadium Name, City"
    private MatchStatusEnum status; // e.g., "scheduled", "in_progress", "completed"
    private String competition; // e.g., "League Name", "Cup Name"
    private String score; // e.g., "2-1", "0-0"
    private String referee; // Optional, can be null if not applicable
    private String notes; // Optional, can be used for additional information about the match
    private String highlightsUrl; // Optional, can be a URL to match highlights or summary
    private MatchTypeEnum matchType;

    private List<ImageDto> images; // Miniaturas, afiches promocionales
    private ImageDto banner;
    private List<MatchIncidentsResponse>incidents;

    private String legGroupId;         // Para vincular ida y vuelta
    private Integer leg;               // 1 = ida, 2 = vuelta
    private String globalScore;        // Ej: "3 - 2" (marcador global final)
    private boolean globalScoreFinal;
}
