package com.junior.boletapp.common.models;

import com.junior.boletapp.common.enums.ImageTypeEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Setter
@Builder
@Document(collection = "images")
public class Image implements Serializable {
    @Id
    private String id;
    private String name;
    private String url;
    private ImageTypeEnum type; // e.g., "profile", "banner", etc.
    private String description; // Optional description of the image
}
