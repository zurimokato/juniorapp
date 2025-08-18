package com.junior.boletapp.common.repository;

import com.junior.boletapp.common.models.Image;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends MongoRepository<Image,String> {
}
