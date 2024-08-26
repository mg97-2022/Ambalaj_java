package com.Ambalaj.Ambalaj.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class JpaFeatures {
    public Pageable getPaginationWithSort(Integer page, Integer pageSize, String sortBy, String sortDirection) {
        Sort sort = Sort.unsorted();
        if (sortBy != null && !sortBy.isEmpty()) {
            Sort.Direction direction =
                    (sortDirection != null && sortDirection.equalsIgnoreCase("desc")) ? Sort.Direction.DESC :
                            Sort.Direction.ASC;
            sort = Sort.by(direction, sortBy);
        }
        return PageRequest.of(page, pageSize, sort);
    }
}
