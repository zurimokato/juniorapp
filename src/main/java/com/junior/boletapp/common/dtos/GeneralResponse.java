package com.junior.boletapp.common.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GeneralResponse <T>{
    private String message;
    private T data;
    private boolean success; // Default to true, can be set to false in case of errors
    private int statusCode;
    private PageResponse pageResponse;

    public static GeneralResponse<Object> success(Object data){
        return GeneralResponse.builder()
                .message("Operation completed successfully")
                .success(true)
                .statusCode(200)
                .data(data)// HTTP 200 OK
                .build();
    }
}
