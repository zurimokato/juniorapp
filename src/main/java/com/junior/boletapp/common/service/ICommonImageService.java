package com.junior.boletapp.common.service;

import com.junior.boletapp.common.models.Image;

public interface ICommonImageService {
    Image saveImage(Image image);
    Image getImageById(String id);
    void deleteImageById(String id);
    Image updateImage(Image image);
}
