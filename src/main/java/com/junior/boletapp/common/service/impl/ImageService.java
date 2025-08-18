package com.junior.boletapp.common.service.impl;

import com.junior.boletapp.common.models.Image;
import com.junior.boletapp.common.repository.ImageRepository;
import com.junior.boletapp.common.service.ICommonImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageService implements ICommonImageService {

    private final ImageRepository imageRepository;
    @Override
    public Image saveImage(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public Image getImageById(String id) {
    if (id == null || id.isEmpty()) {
            return null;
        }
        return imageRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteImageById(String id) {
        if (id != null && !id.isEmpty()) {
            imageRepository.deleteById(id);
        }

    }

    @Override
    public Image updateImage(Image image) {
        if (image == null || image.getId() == null || image.getId().isEmpty()) {
            return null;
        }
        Image existingImage = imageRepository.findById(image.getId())
                .orElseThrow(() -> new IllegalArgumentException("Image not found with id: " + image.getId()));
        existingImage.setUrl(image.getUrl());
        existingImage.setDescription(image.getDescription());
        existingImage.setType(image.getType());
        existingImage.setName(image.getName());
        return imageRepository.save(existingImage);
    }
}
