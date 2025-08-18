package com.junior.boletapp.matches.mappers;

import com.junior.boletapp.matches.controller.request.MatchIncidentRequest;
import com.junior.boletapp.matches.controller.response.MatchIncidentsResponse;
import com.junior.boletapp.matches.model.MatchIncident;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MatchIncidentMapper {
    MatchIncident toModel(MatchIncidentRequest matchIncident);
    MatchIncidentsResponse toResponse(MatchIncident matchIncident);
}
