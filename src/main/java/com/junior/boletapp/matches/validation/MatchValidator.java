package com.junior.boletapp.matches.validation;

import com.junior.boletapp.matches.constants.MatchStatusEnum;
import com.junior.boletapp.matches.model.Match;
import org.springframework.stereotype.Component;

@Component
public class MatchValidator {

    public  void validate(Match match){
        if (match == null) {
            throw new IllegalArgumentException("Match cannot be null");
        }
        if (match.getHomeTeam() == null || match.getAwayTeam() == null) {
            throw new IllegalArgumentException("Both teams must be specified for a match");
        }
        if (match.getHomeTeam().equals(match.getAwayTeam())) {
            throw new IllegalArgumentException("Home team and away team cannot be the same");
        }
        if (match.getStatus() == null) {
            match.setStatus(MatchStatusEnum.SCHEDULED);
        }
        if (match.getDate() == null && match.getStatus() == MatchStatusEnum.SCHEDULED) {
            throw new IllegalArgumentException("Match date cannot be null");
        }
    }

    public void validateMatchUpdate(String id, Match incomingMatch) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Match ID cannot be null or empty.");
        }

        if (incomingMatch == null) {
            throw new IllegalArgumentException("Match cannot be null.");
        }
    }

    public void validatePostponedTransition(Match existingMatch, Match incomingMatch) {
        MatchStatusEnum current = existingMatch.getStatus();
        MatchStatusEnum incoming = incomingMatch.getStatus();

        if (current == MatchStatusEnum.POSTPONED) {
            if (incoming == MatchStatusEnum.SCHEDULED) {
                if (incomingMatch.getDate() == null || incomingMatch.getStartTime() == null) {
                    throw new IllegalArgumentException("To reschedule a postponed match, both date and start time are required.");
                }
            } else if (incoming != MatchStatusEnum.POSTPONED) {
                throw new IllegalStateException("A postponed match can only be changed to SCHEDULED.");
            }
        }
    }


}
