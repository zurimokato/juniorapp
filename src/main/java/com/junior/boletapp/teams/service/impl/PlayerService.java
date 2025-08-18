package com.junior.boletapp.teams.service.impl;

import com.junior.boletapp.teams.model.Player;
import com.junior.boletapp.teams.repository.PlayerRepository;
import com.junior.boletapp.teams.service.IPlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PlayerService implements IPlayerService {
    private final PlayerRepository playerRepository;

    @Override
    @CachePut(value = "players", key = "#player.fullName")
    public Player createPlayer(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }

        return playerRepository.save(player);
    }

    @Override
    @Cacheable(value = "players", key = "#id")
    public Player getPlayerById(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Player ID cannot be null or empty");
        }
        return playerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Player not found with ID: " + id));
    }


    @Override
    @Cacheable(value = "players")
    public List<Player> getAllPlayers() {
        List<Player> players = playerRepository.findAll();
        if (players.isEmpty()) {
            throw new IllegalArgumentException("No players found");
        }
        return players;
    }

    @Override
    @CacheEvict(value = "players", key = "#id")
    public Player updatePlayer(String id, Player player) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Player ID cannot be null or empty");
        }
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        Player existingPlayer = playerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Player not found with ID: " + id));

        // Update fields as necessary
        existingPlayer.setFullName(player.getFullName());
        existingPlayer.setPosition(player.getPosition());
        existingPlayer.setNumber(player.getNumber());
        existingPlayer.setNationality(player.getNationality());

        if (player.getPhoto() != null) {
            existingPlayer.setPhoto(player.getPhoto());
        }


        return playerRepository.save(existingPlayer);
    }

    @Override
    @CacheEvict(value = "players", key = "#id")
    public void deletePlayer(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Player ID cannot be null or empty");
        }
        if (!playerRepository.existsById(id)) {
            throw new IllegalArgumentException("Player not found with ID: " + id);
        }
        playerRepository.deleteById(id);

    }
}
