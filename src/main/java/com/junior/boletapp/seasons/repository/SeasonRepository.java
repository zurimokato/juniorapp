package com.junior.boletapp.seasons.repository;

import com.junior.boletapp.seasons.model.Season;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeasonRepository extends MongoRepository<Season, String> {
}
