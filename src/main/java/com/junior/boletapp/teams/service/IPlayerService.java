package com.junior.boletapp.teams.service;

import com.junior.boletapp.teams.model.Player;

import java.util.List;

public interface IPlayerService {
    // Define methods for player-related operations here
    // For example:
    Player createPlayer(Player player);

    Player getPlayerById(String id);

    List<Player> getAllPlayers();

    Player updatePlayer(String id, Player player);

    void deletePlayer(String id);
}
