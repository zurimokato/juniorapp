package com.junior.boletapp.teams.repository;

import com.junior.boletapp.teams.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerRepository  extends MongoRepository<Player,String> {
}
