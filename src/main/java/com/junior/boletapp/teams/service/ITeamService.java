package com.junior.boletapp.teams.service;

import com.junior.boletapp.teams.model.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITeamService {
    Team addTeam(Team team);
    Team getTeamById(String id);
    Team updateTeam(String id, Team team);
    void deleteTeam(String id);
    Team getTeamByName(String name);
    Page<Team>getTeams(Pageable pageable);
    List<Team> addTeams(List<Team> teams);
    List<Team> getAllTeams();
}
