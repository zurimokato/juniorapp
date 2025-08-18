package com.junior.boletapp.common.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class PageResponse {
    private int pageNumber; // Current page number
    private int pageSize; // Number of items per page
    private long totalElements; // Total number of elements across all pages
    private int totalPages; // Total number of pages
}
