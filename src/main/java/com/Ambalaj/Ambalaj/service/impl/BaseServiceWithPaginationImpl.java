package com.Ambalaj.Ambalaj.service.impl;

import com.Ambalaj.Ambalaj.service.BaseServiceWithPagination;
import com.Ambalaj.Ambalaj.utils.JpaFeatures;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public abstract class BaseServiceWithPaginationImpl<T, Id> extends BaseServiceImpl<T, Id> implements BaseServiceWithPagination<T, Id> {
    @Autowired
    private JpaFeatures jpaFeatures;

    protected abstract JpaSpecificationExecutor<T> getSpecificationExecutor();

    protected abstract Specification<T> getSearchSpecification(String search);

    @Override
    public Page<T> getPaginatedSortedSearchableEntities(Integer page, Integer pageSize, String sortBy,
                                                        String sortDirection, String search) {
        Pageable pageable = jpaFeatures.getPaginationWithSort(page, pageSize, sortBy, sortDirection);
        if (search != null && !search.trim().isEmpty()) {
            Specification<T> specification = getSearchSpecification(search);
            return getSpecificationExecutor().findAll(specification, pageable);
        }
        return getRepository().findAll(pageable);
    }
}
