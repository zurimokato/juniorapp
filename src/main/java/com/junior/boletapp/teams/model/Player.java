package com.junior.boletapp.teams.model;

import com.junior.boletapp.common.models.Image;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Setter
@Builder
@Document(collection = "players")
public class Player implements Serializable {
    @Id
    private String id;
    private String fullName;
    private String position;
    private int number;
    private String nationality;
    private @DBRef Image photo;
}