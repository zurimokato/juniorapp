package com.junior.boletapp.seasons.controller.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SimpleSeasonResponse {
    private String id;
    private String name;
    private String startDate;
    private String endDate;
}
