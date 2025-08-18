package com.junior.boletapp.matches.model;

import com.junior.boletapp.matches.constants.MatchIncidentTypeEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@Document(collection = "match_incidents")
public class MatchIncident {

    @Id
    private String id;
    private String minute;
    private MatchIncidentTypeEnum type; // goal, card, substitution, etc.
    private String detail;
    private boolean localTeam; // true if the incident is for the home team, false for the away team
    private String playerName;
    private String playerIn;
    private String playerOut;
    private String score;
    private String matchId; // Reference to the match this incident belongs to
}
