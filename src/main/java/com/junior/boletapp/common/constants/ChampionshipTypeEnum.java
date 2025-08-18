package com.junior.boletapp.common.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum ChampionshipTypeEnum {
    NATIONAL("NATIONAL_LEAGUE"),
    NATIONAL_CUP("NATIONAL_CUP"),
    NATIONAL_SUPERCUP("NATIONAL_SUPERCUP"),
    COPA_LIBERTADORES("COPA_LIBERTADORES"),
    COPA_SUDAMERICANA("COPA_SUDAMERICANA");
    private final String value;

    ChampionshipTypeEnum(String value) {
        this.value = value;
    }

    @JsonCreator
    public static ChampionshipTypeEnum fromValue(String value) {
        for (ChampionshipTypeEnum type : ChampionshipTypeEnum.values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown ChampionshipTypeEnum: " + value);
    }
}
