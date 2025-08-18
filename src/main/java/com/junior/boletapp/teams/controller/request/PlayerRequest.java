package com.junior.boletapp.teams.controller.request;


import com.junior.boletapp.common.dtos.ImageDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PlayerRequest {
    private String id;
    private String fullName;
    private String position;
    private int number;
    private String nationality;
    private ImageDto photo;
}
