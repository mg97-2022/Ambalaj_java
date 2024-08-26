package com.Ambalaj.Ambalaj.service;

import org.springframework.data.domain.Page;

public interface BaseServiceWithPagination<T, Id> extends BaseService<T, Id> {
    Page<T> getPaginatedSortedSearchableEntities(Integer page, Integer pageSize, String sortBy,
                                                 String sortDirection, String search);
}
