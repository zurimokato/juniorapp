package com.junior.boletapp.teams.controller.request;

import com.junior.boletapp.common.dtos.ImageDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class TeamRequest {
    private String id;
    private String name;
    private String surname;
    private ImageDto flag;
    private String teamUrl;
    private ImageDto shield;

    private LocalDate foundationDate;
    private List<String> socialMediaUrls;
    private List<ChampionshipRequest> championships; // Campeonatos ganados

    private List<ImageDto> gallery; // Galería de imágenes
    private  List<PlayerRequest>players; // Jugadores del equipo
}
