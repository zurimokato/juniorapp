package com.junior.boletapp.teams.controller.response;


import com.junior.boletapp.common.dtos.ImageDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class SimpleTeamResponse {

    private String id;
    private String name;
    private String surname;
    private ImageDto flag;
    private String teamUrl;
    private ImageDto shield;

    private LocalDate foundationDate;
    private List<String> socialMediaUrls;
}
