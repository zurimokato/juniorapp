package com.junior.boletapp.teams.service.impl;

import com.junior.boletapp.app.exceptions.BadRequestException;
import com.junior.boletapp.app.exceptions.NotFoundException;
import com.junior.boletapp.common.models.Image;
import com.junior.boletapp.common.service.impl.ImageService;
import com.junior.boletapp.teams.model.Championship;
import com.junior.boletapp.teams.model.Player;
import com.junior.boletapp.teams.repository.TeamRepository;
import com.junior.boletapp.teams.model.Team;
import com.junior.boletapp.teams.service.IChampionshipService;
import com.junior.boletapp.teams.service.IPlayerService;
import com.junior.boletapp.teams.service.ITeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@Service
@RequiredArgsConstructor
public class TeamService implements ITeamService {
    private final ImageService imageService;
    private final IPlayerService playerService;
    private final IChampionshipService championshipService;
    private final TeamRepository teamRepository;
    private final Logger logger = Logger.getLogger(TeamService.class.getName());
    @Override
    @CachePut(value = "teams", key = "#team.name")
    public Team addTeam(Team team) {
        if (team == null) {
            throw new BadRequestException("Team cannot be null");
        }
        crearEntity(team);
        Team savedTeam = teamRepository.save(team);

        clearTeamPagesCache();

        logger.info(savedTeam.toString());

        return savedTeam;
    }

    @Override
    @Cacheable(value = "teams", key = "#id")
    public Team getTeamById(String id) {
        if (id == null || id.isEmpty()) {
            throw new BadRequestException("Team ID cannot be null or empty");
        }
        return teamRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Team not found with ID: " + id));
    }


    @Override
    @CacheEvict(value = "teams", key = "#id")
    public Team updateTeam(String id, Team team) {
        if (id == null || id.isEmpty()) {
            throw new BadRequestException("Team ID cannot be null or empty");
        }
        if (team == null) {
            throw new BadRequestException("Team cannot be null");
        }

        Team existingTeam = teamRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Team not found with ID: " + id));

        if (existingTeam.getFlag() != null) {
            team.setFlag(imageService.updateImage(team.getFlag()));
        }
        if (existingTeam.getShield() != null) {
            team.setShield(imageService.updateImage(team.getShield()));
        }
        if(existingTeam.getPlayers() != null) {
            team.getPlayers().forEach(player -> {
                if (player.getPhoto() != null) {
                    player.setPhoto(imageService.updateImage(player.getPhoto()));
                }
                playerService.createPlayer(player);
            });
        }

        if (existingTeam.getChampionships() != null) {
            team.getChampionships().forEach(championship -> championshipService.updateChampionship(championship.getId(),championship));
        }
        logger.info("existingTeam:  "+ existingTeam);
        Team updatedTeam = teamRepository.save(team);
        clearTeamPagesCache();
        return updatedTeam;

    }

    @Override
    @CacheEvict(value = "teams", key = "#id")
    public void deleteTeam(String id) {
        if (id == null || id.isEmpty()) {
            throw new BadRequestException("Team ID cannot be null or empty");
        }
        if (!teamRepository.existsById(id)) {
            throw new NotFoundException("Team not found with ID: " + id);
        }
        teamRepository.deleteById(id);
        clearTeamPagesCache();

    }

    @Override
    @CacheEvict(value = "teams", key = "#name")
    public Team getTeamByName(String name) {
        if (name == null || name.isEmpty()) {
            throw new BadRequestException("Team name cannot be null or empty");
        }
        return teamRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Team not found with name: " + name));
    }


    @Override
    @Cacheable(value = "teamsPages", key = "'page:' + #pageable.pageNumber + ':size:' + #pageable.pageSize + ':sort:' + #pageable.sort.toString()")
    public Page<Team> getTeams(Pageable pageable) {
        return teamRepository.findAll(pageable);
    }

    @Override
    public List<Team> addTeams(List<Team> teams) {
        List<Team>saved= new ArrayList<>();
        if (teams == null || teams.isEmpty()) {
            throw new BadRequestException("Teams cannot be null or empty");
        }
        teams.forEach(team -> {
            crearEntity(team);
            saved.add(teamRepository.save(team));
        });
       return saved;
    }

    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    private void crearEntity(Team team) {
        if (team.getFlag() != null) {
            team.setFlag(imageService.saveImage(team.getFlag()));
        }
        if (team.getShield() != null) {
            team.setShield(imageService.saveImage(team.getShield()));
        }
        if(team.getPlayers() != null) {
            List<Player> playerSaved=new ArrayList<>();
            team.getPlayers().forEach(player -> {
                if (player.getPhoto() != null) {
                    player.setPhoto(imageService.saveImage(player.getPhoto()));
                }
                playerService.createPlayer(player);
                playerSaved.add(player);
            });
            team.setPlayers(playerSaved);
        }

        if (team.getChampionships() != null) {
            List<Championship>championshipSaved=new ArrayList<>();

            team.getChampionships().forEach(championship -> championshipSaved.add(championshipService.createChampionship(championship)));
            team.setChampionships(championshipSaved);
        }

        if (team.getGallery() != null) {
            List<Image>gallerySaved=new ArrayList<>();
            team.getGallery().forEach(image -> gallerySaved.add(imageService.saveImage(image)));
            team.setGallery(gallerySaved);
        }
    }


    @CacheEvict(value = "teamsPages", allEntries = true)
    public void clearTeamPagesCache() {
        logger.info("Caché de páginas limpiado");
    }
}
