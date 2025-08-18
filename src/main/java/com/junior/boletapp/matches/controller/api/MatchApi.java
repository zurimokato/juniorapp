package com.junior.boletapp.matches.controller.api;

import com.junior.boletapp.matches.controller.request.MatchRequest;
import com.junior.boletapp.matches.controller.request.UpdateScoreRequest;
import com.junior.boletapp.matches.controller.response.MatchResponse;
import com.junior.boletapp.matches.controller.response.SimpleMatchResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/matches")
@Tag(name = "Matches", description = "Operaciones relacionadas con los partidos")
public interface MatchApi {

    @PostMapping
    @Operation(summary = "Registrar un nuevo partido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Partido creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content)
    })
    ResponseEntity<MatchResponse> addMatch(@RequestBody @Valid MatchRequest matchRequest);

    @GetMapping
    @Operation(summary = "Obtener todos los partidos paginados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de partidos obtenida")
    })
    ResponseEntity<PagedModel<EntityModel<SimpleMatchResponse>>> getMatches(Pageable pageable, PagedResourcesAssembler<SimpleMatchResponse> assembler);
    @GetMapping("/{id}")
    @Operation(summary = "Obtener un partido por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Partido encontrado"),
            @ApiResponse(responseCode = "404", description = "Partido no encontrado")
    })
    ResponseEntity<MatchResponse> getMatchById(@PathVariable String id);
    @GetMapping("/by-teams/{team1}/{team2}")
    @Operation(summary = "Obtener partidos entre dos equipos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de partidos obtenida")
    })
    ResponseEntity<List<MatchResponse>> getMatchesByTeams(@PathVariable String team1, @PathVariable String team2);
    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Partido actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Partido no encontrado")
    })
    ResponseEntity<MatchResponse> updateMatch(@PathVariable String id, @RequestBody @Valid MatchRequest matchRequest);
    @PutMapping("/{id}/update-score")
    @Operation(summary = "Actualizar el marcador (score) de un partido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Marcador actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Partido no encontrado")
    })
    ResponseEntity<MatchResponse> updateMatchScore(@PathVariable String id,
                                                   @RequestBody UpdateScoreRequest updateScoreRequest);
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un partido por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Partido eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Partido no encontrado")
    })
    ResponseEntity<Void> deleteMatch(@PathVariable String id);

}
