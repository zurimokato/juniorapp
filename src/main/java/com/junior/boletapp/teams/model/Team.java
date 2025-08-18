package com.junior.boletapp.teams.model;

import com.junior.boletapp.common.models.Image;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@Document(collection = "teams")
public class Team implements Serializable {
    @Id
    private String id;
    private String name;
    private String surname;
    private Image flag;
    private String teamUrl;
    private @DBRef Image shield;
    private LocalDate foundationDate;
    private List<String> socialMediaUrls;

    @DBRef
    private List<Championship> championships; // Campeonatos ganados

    @DBRef
    private List<Image> gallery; // Galería de imágenes

    @DBRef
    private  List<Player> players; // Jugadores del equipo

}
