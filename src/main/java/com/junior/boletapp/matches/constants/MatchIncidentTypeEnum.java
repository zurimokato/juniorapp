package com.junior.boletapp.matches.constants;

import lombok.Getter;

@Getter
public enum MatchIncidentTypeEnum {
    GOAL("GOL"),
    YELLOW_CARD("Tarjeta Amarilla"),
    RED_CARD("Tarjeta Roja"),
    ASSIST("Asistencia"),
    PENALTY("Penalti"),
    OWN_GOAL("Gol en propia puerta"),
    SUBSTITUTION("Sustitución"),
    LESION("Lesión"),;

    private final String description;

    MatchIncidentTypeEnum(String description) {
        this.description = description;
    }

}
