package com.junior.boletapp.matches.validation;

import com.junior.boletapp.app.exceptions.AppException;
import com.junior.boletapp.app.exceptions.BadRequestException;
import com.junior.boletapp.app.exceptions.NotFoundException;
import com.junior.boletapp.matches.constants.MatchStatusEnum;
import com.junior.boletapp.matches.model.Match;
import com.junior.boletapp.matches.model.MatchIncident;
import org.springframework.stereotype.Component;

@Component
public class MatchIncidentValidator {

    public void validateIncident(MatchIncident incident, String matchId) throws AppException {
        if (incident == null || matchId == null || matchId.isEmpty()) {
            throw new BadRequestException("Match ID and incident cannot be null or empty");
        }
    }
    public void validateIncident(Match match,MatchIncident incident) {
        if (incident == null) {
            throw new AppException("Incident cannot be null");
        }
        if(match.getStatus()!= MatchStatusEnum.ONGOING) {
            throw new AppException("Cannot add incident to a match that is not ongoing");
        }
    }
}
