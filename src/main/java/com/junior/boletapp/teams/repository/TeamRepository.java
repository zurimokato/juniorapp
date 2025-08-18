package com.junior.boletapp.teams.repository;

import com.junior.boletapp.teams.model.Team;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends MongoRepository<Team,String> {

    Optional<Team> findByName(String name);
    boolean existsByName(String name);

}
