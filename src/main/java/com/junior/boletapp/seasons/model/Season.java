package com.junior.boletapp.seasons.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Builder
@Document(collection = "seasons")
public class Season {
    @Id
    private String id;
    private String name;
    private String startDate;
    private String endDate;
    private List<Tournament> tournaments;

}
