package com.junior.boletapp.teams.service.impl;

import com.junior.boletapp.teams.model.Championship;
import com.junior.boletapp.teams.repository.ChampionshipRepository;
import com.junior.boletapp.teams.service.IChampionshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChampionshipService implements IChampionshipService {
    private final ChampionshipRepository championshipRepository;

    @Override
    @CachePut(value = "champions", key = "#championship.year")
    public Championship createChampionship(Championship championship) {
        if (championship == null) {
            throw new IllegalArgumentException("championship is null");
        }

        return championshipRepository.save(championship);
    }

    @Override
    @Cacheable(value = "champions", key = "#id")
    public Championship getChampionshipById(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Championship ID cannot be null or empty");
        }

        return championshipRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Championship not found with ID: {} " + id)
        );


    }

    @Override
    @Cacheable(value = "champions")
    public List<Championship> getAllChampionships() {
        List<Championship> championships = championshipRepository.findAll();
        if (championships.isEmpty()) {
            throw new RuntimeException("No championships found");

        }
        return championships;
    }

    @Override
    @CacheEvict(value = "champions", key = "#id")
    public Championship updateChampionship(String id, Championship championship) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Championship ID cannot be null or empty");
        }
        if (championship == null) {
            throw new IllegalArgumentException("Championship cannot be null");
        }
        Championship existingChampionship = championshipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Championship not found with ID: " + id));
        existingChampionship.setName(championship.getName());
        existingChampionship.setDescription(championship.getDescription());
        existingChampionship.setYear(championship.getYear());
        existingChampionship.setType(championship.getType());
        return championshipRepository.save(existingChampionship);
    }

    @Override
    @CacheEvict(value = "champions", key = "#id")
    public void deleteChampionship(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Championship ID cannot be null or empty");
        }
        Championship championship = championshipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Championship not found with ID: " + id));
        championshipRepository.delete(championship);

    }
}
