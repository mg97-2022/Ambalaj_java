package com.Ambalaj.Ambalaj.service.impl;

import com.Ambalaj.Ambalaj.model.CategoryEntity;
import com.Ambalaj.Ambalaj.repository.CategoryRepository;
import com.Ambalaj.Ambalaj.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl extends BaseServiceImpl<CategoryEntity, Long> implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public JpaRepository<CategoryEntity, Long> getRepository() {
        return categoryRepository;
    }

    @Override
    public List<CategoryEntity> getCategoriesByIds(List<Long> categoryIds) {
        return getEntitiesByIds(categoryIds, "Categories");
    }

    @Override
    public Long getId(CategoryEntity categoryEntity) {
        return categoryEntity.getId();
    }

    @Override
    protected void updateEntityFields(CategoryEntity existingEntity, CategoryEntity updatedEntity) {

    }
}


