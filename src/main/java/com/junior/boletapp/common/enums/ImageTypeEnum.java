package com.junior.boletapp.common.enums;

import lombok.Getter;

@Getter
public enum ImageTypeEnum {
    MATCH("match"),
    TEAM("team"),
    PLAYER("player"),
    BANNER("banner"),
    PROFILE("profile"),
    LOGO_TEAM("logo_team"),
    LOGO_PLAYER("logo_player"),
    GALLERY("gallery"),
    SHIELD("shield"),
    FLAG("flag"),
    STADIUM_LOGO("stadium_logo"),
    STADIUM_IMAGE("stadium_image"),
    STADIUM_BANNER("stadium_banner"),
    STADIUM_PROFILE("stadium_profile"),
    LOGO("logo"),
    STADIUM("stadium"),
    OTHER("other");
    private final String type;
    ImageTypeEnum(String type) {
        this.type = type;
    }
}
