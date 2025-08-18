package com.junior.boletapp.seasons.repository;

import com.junior.boletapp.seasons.model.Tournament;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TournamentRepository extends MongoRepository<Tournament, String> {

    List<Tournament> findBySeasonId(String seasonId);
    Page<Tournament> findAllBySeasonId(String seasonId, Pageable pageable);

}
