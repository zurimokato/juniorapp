package com.junior.boletapp.matches.model;

import com.junior.boletapp.common.models.Image;
import com.junior.boletapp.matches.constants.MatchStatusEnum;
import com.junior.boletapp.matches.constants.MatchTypeEnum;
import com.junior.boletapp.teams.model.Team;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Builder
@Document(collection = "matches")
public class Match {
    @Id
    private String id;
    private @DBRef Team homeTeam;
    private @DBRef Team awayTeam;
    private LocalDate date;
    private LocalTime startTime;
    private String location;// e.g., "Stadium Name, City"
    private MatchStatusEnum status; // e.g., "scheduled", "in_progress", "completed"
    private String competition; // e.g., "League Name", "Cup Name"
    private String score; // e.g., "2-1", "0-0"
    private String referee; // Optional, can be null if not applicable
    private String notes; // Optional, can be used for additional information about the match
    private String highlightsUrl; // Optional, can be a URL to match highlights or summary
    private MatchTypeEnum matchType;

    private @DBRef List<Image> images; // Miniaturas, afiches promocionales
    private @DBRef Image banner;// e.g., "friendly", "league", "cup",
    private @DBRef List<MatchIncident> incidents;


    private String legGroupId;         // Para vincular ida y vuelta
    private Integer leg;               // 1 = ida, 2 = vuelta
    private String globalScore;        // Ej: "3 - 2" (marcador global final)
    private boolean globalScoreFinal; // Indica si el marcador global es finalizado
}
