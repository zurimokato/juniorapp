package com.junior.boletapp.teams.controller.api;

import com.junior.boletapp.teams.controller.request.TeamRequest;
import com.junior.boletapp.teams.controller.response.SimpleTeamResponse;
import com.junior.boletapp.teams.controller.response.TeamResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Teams", description = "Operaciones relacionadas con los equipos de fútbol")
public interface TeamApiConfig {
    @GetMapping("paginated")
    @Operation(summary = "Obtener todos los equipos paginados")
    @ApiResponse(responseCode = "200", description = "Equipos obtenidos exitosamente")
    ResponseEntity<PagedModel<EntityModel<SimpleTeamResponse>>> getTeams(Pageable pageable, PagedResourcesAssembler<SimpleTeamResponse> assembler);


    @GetMapping
    @Operation(summary = "Obtener todos los equipos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Equipos obtenidos exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se encontraron equipos", content = @Content)
    })
    ResponseEntity<List<SimpleTeamResponse>> getAllTeams();



    @GetMapping("/{id}")
    @Operation(summary = "Obtener un equipo por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Equipo encontrado"),
            @ApiResponse(responseCode = "404", description = "Equipo no encontrado", content = @Content)
    })
    ResponseEntity<TeamResponse> getTeamById(@PathVariable String id);
    @GetMapping("/name/{name}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Equipo encontrado"),
            @ApiResponse(responseCode = "404", description = "Equipo no encontrado", content = @Content)
    })
    ResponseEntity<TeamResponse> getTeamByName(@PathVariable String name);
    @PostMapping
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Equipo creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content)
    })
    ResponseEntity<TeamResponse> addTeam(@RequestBody TeamRequest teamResponse);

    @PostMapping("/bulk")
    @Operation(summary = "Crear múltiples equipos")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Equipos creados exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content)
    })
    ResponseEntity<List<SimpleTeamResponse>> addTeams(@RequestBody List<TeamRequest> teamResponses);

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un equipo existente por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Equipo actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Equipo no encontrado", content = @Content)
    })
    ResponseEntity<TeamResponse> updateTeam(@PathVariable String id, @RequestBody TeamRequest teamResponse);
    @DeleteMapping("/{id}/delete")
    @Operation(summary = "Eliminar un equipo por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Equipo eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Equipo no encontrado", content = @Content)
    })
    ResponseEntity<Void> deleteTeam(@PathVariable String id);
}
