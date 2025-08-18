package com.junior.boletapp.common.dtos;

import com.junior.boletapp.common.enums.ImageTypeEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ImageDto {

    private String id;
    private String name;
    private String url;
    private ImageTypeEnum type; // e.g., "profile", "banner", etc.
    private String description; // Optional description of the image
}
