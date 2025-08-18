package com.junior.boletapp.matches.controller.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateScoreRequest {
    private String score;
}
