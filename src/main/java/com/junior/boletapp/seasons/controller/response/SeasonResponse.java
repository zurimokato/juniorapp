package com.junior.boletapp.seasons.controller.response;

import com.junior.boletapp.seasons.model.Tournament;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class SeasonResponse {
    private String id;
    private String name;
    private String startDate;
    private String endDate;
    private List<Tournament> tournaments;
}
