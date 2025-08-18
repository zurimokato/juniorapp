package com.junior.boletapp.seasons.controller.request;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class SeasonRequest {
    private String id;
    private String name;
    private String startDate;
    private String endDate;
    private List<TournamentRequest> tournaments;
}
