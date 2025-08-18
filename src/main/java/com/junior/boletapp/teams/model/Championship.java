package com.junior.boletapp.teams.model;

import com.junior.boletapp.common.constants.ChampionshipTypeEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Setter
@Builder
@Document(collection = "championships")
public class Championship implements Serializable {
    @Id
    private String id;
    private String name;
    private String year;
    private String description;
    private ChampionshipTypeEnum type;

}