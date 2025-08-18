package com.junior.boletapp.seasons.model;

import com.junior.boletapp.common.models.Image;
import com.junior.boletapp.matches.constants.MatchTypeEnum;
import com.junior.boletapp.matches.model.Match;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Getter
@Setter
@Builder
@Document(collection = "tournaments")
public class Tournament {
    @Id
    private String id;
    private String name;
    private String description;
    private @DBRef Image logo; // URL to the tournament logo or image
    private String startDate; // e.g., "2023-01-01"
    private String endDate; // e.g., "2023-12-31"
    private MatchTypeEnum tournamentType; // e.g., "league", "knockout", "friendly"
    private @DBRef Set<Match> matches;
    private String seasonId;
}
