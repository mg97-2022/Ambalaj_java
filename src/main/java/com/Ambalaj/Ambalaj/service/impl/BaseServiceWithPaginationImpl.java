package com.Ambalaj.Ambalaj.service.impl;

import com.Ambalaj.Ambalaj.service.BaseServiceWithPagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public abstract class BaseServiceWithPaginationImpl<T, Id> extends BaseServiceImpl<T, Id> implements BaseServiceWithPagination<T, Id> {
    protected abstract JpaSpecificationExecutor<T> getSpecificationExecutor();

    protected abstract Specification<T> getSearchSpecification(String search);

    @Override
    public Page<T> getPaginatedSortedSearchableEntities(Integer page, Integer pageSize, String sortBy,
                                                        String sortDirection, String search) {
        Sort sort = Sort.unsorted();
        if (sortBy != null && !sortBy.isEmpty()) {
            Sort.Direction direction =
                    (sortDirection != null && sortDirection.equalsIgnoreCase("desc")) ? Sort.Direction.DESC :
                            Sort.Direction.ASC;
            sort = Sort.by(direction, sortBy);
        }
        Pageable pageable = PageRequest.of(page, pageSize, sort);

        if (search != null && !search.trim().isEmpty()) {
            Specification<T> specification = getSearchSpecification(search);
            return getSpecificationExecutor().findAll(specification, pageable);
        }

        return getRepository().findAll(pageable);
    }
}
