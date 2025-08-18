package com.junior.boletapp.matches.controller.request;

import com.junior.boletapp.matches.constants.MatchIncidentTypeEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MatchIncidentRequest {
    private String id;
    private String minute;
    private MatchIncidentTypeEnum type; // goal, card, substitution, etc.
    private String detail; // yellow, red, yellowRed
    private boolean localTeam;
    private String playerName;
    private String playerIn;
    private String playerOut;
    private String score;
    private String matchId; // Reference to the match this incident belongs to

}
