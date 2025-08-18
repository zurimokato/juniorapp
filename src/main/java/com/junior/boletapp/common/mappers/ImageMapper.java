package com.junior.boletapp.common.mappers;

import com.junior.boletapp.common.dtos.ImageDto;
import com.junior.boletapp.common.models.Image;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    ImageDto toDto(Image team);
    Image toEntity(ImageDto teamDto);
}
