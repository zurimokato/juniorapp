package com.junior.boletapp.teams.repository;

import com.junior.boletapp.teams.model.Championship;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChampionshipRepository extends MongoRepository<Championship,String> {
}
