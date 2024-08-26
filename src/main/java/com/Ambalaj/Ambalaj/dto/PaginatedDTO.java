package com.Ambalaj.Ambalaj.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class PaginatedDTO<T> {
    private List<T> data;
    private Integer pageSize;
    private Integer currentPage;
    private Long totalPages;
    private Long totalNumber;
}
