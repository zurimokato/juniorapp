package com.junior.boletapp.matches.constants;

import lombok.Getter;

@Getter
public enum MatchTypeEnum {
    NATIONAL_LEAGUE("Liga nacional"),
    NATIONAL_CUP("Copa nacional"),
    SUPER_LEAGUE("Superliga"),
    FRIENDLY("Amistoso"),
    COPA_LIBERTADORES("Copa Libertadores"),
    COPA_SUDAMERICANA("Copa Sudamericana"),
    INTERNATIONAL("International");

    private final String type;

    MatchTypeEnum(String type) {
        this.type = type;
    }

}
