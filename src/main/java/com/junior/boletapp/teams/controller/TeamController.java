package com.junior.boletapp.teams.controller;

import com.junior.boletapp.teams.controller.api.TeamApiConfig;
import com.junior.boletapp.teams.controller.request.TeamRequest;
import com.junior.boletapp.teams.controller.response.SimpleTeamResponse;
import com.junior.boletapp.teams.controller.response.TeamResponse;
import com.junior.boletapp.teams.mappers.TeamMapper;
import com.junior.boletapp.teams.service.ITeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teams")
@RequiredArgsConstructor
public class TeamController implements TeamApiConfig {
    private final ITeamService teamService;
    private final TeamMapper teamMapper;
    @Override
    public ResponseEntity<PagedModel<EntityModel<SimpleTeamResponse>>> getTeams(Pageable pageable, PagedResourcesAssembler<SimpleTeamResponse> assembler) {
        Page<SimpleTeamResponse> teamResponses = teamService.getTeams(pageable)
                .map(teamMapper::toSimpleResponse);
        return ResponseEntity.ok(assembler.toModel(teamResponses));
    }

    @Override
    public ResponseEntity<List<SimpleTeamResponse>> getAllTeams() {
        List<SimpleTeamResponse>responseList= teamService.getAllTeams()
                .stream()
                .map(teamMapper::toSimpleResponse)
                .toList();
        return ResponseEntity.ok(responseList);
    }

    @Override
    public ResponseEntity<TeamResponse> getTeamById(String id) {
        if (id == null || id.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        TeamResponse teamResponse = teamMapper.toResponse(teamService.getTeamById(id));
        if (teamResponse == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(teamResponse);
    }

    @Override
    public ResponseEntity<TeamResponse> getTeamByName(String name) {
        if (name == null || name.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        TeamResponse teamResponse = teamMapper.toResponse(teamService.getTeamByName(name));
        if (teamResponse == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(teamResponse);
    }

    @Override
    public ResponseEntity<TeamResponse> addTeam(TeamRequest teamResponse) {
        if (teamResponse == null) {
            return ResponseEntity.badRequest().build();
        }
        TeamResponse savedTeam = teamMapper.toResponse(teamService.addTeam(teamMapper.toEntity(teamResponse)));
        return ResponseEntity.ok(savedTeam);
    }

    @Override
    public ResponseEntity<List<SimpleTeamResponse>> addTeams(List<TeamRequest> teamResponses) {
        if (teamResponses == null || teamResponses.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<SimpleTeamResponse>responses=teamService.addTeams(teamResponses.stream().map(teamMapper::toEntity).toList())
                .stream().map(teamMapper::toSimpleResponse).toList();
        return ResponseEntity.ok(responses);
    }

    @Override
    public ResponseEntity<TeamResponse> updateTeam(String id, TeamRequest teamResponse) {
        if (id == null || id.isEmpty() || teamResponse == null) {
            return ResponseEntity.badRequest().build();
        }
        TeamResponse updatedTeam = teamMapper.toResponse(teamService.updateTeam(id, teamMapper.toEntity(teamResponse)));
        if (updatedTeam == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedTeam);
    }

    @Override
    public ResponseEntity<Void> deleteTeam(String id) {
        if (id == null || id.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        try {
            teamService.deleteTeam(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
