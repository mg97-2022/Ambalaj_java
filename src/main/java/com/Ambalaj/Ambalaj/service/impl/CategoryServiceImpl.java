package com.Ambalaj.Ambalaj.service.impl;

import com.Ambalaj.Ambalaj.model.CategoryEntity;
import com.Ambalaj.Ambalaj.repository.CategoryRepository;
import com.Ambalaj.Ambalaj.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl extends BaseServiceImpl<CategoryEntity, Long> implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public JpaRepository<CategoryEntity, Long> getRepository() {
        return categoryRepository;
    }
}


