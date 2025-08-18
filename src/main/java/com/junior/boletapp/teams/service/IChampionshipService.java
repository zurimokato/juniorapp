package com.junior.boletapp.teams.service;

import com.junior.boletapp.teams.model.Championship;

import java.util.List;

public interface IChampionshipService {
    // Define methods for championship-related operations here
    // For example:
    Championship createChampionship(Championship championship);

    Championship getChampionshipById(String id);

    List<Championship> getAllChampionships();

    Championship updateChampionship(String id, Championship championship);

    void deleteChampionship(String id);
}
