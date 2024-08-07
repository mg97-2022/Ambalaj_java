package com.Ambalaj.Ambalaj.service.impl;

import com.Ambalaj.Ambalaj.exception.NotFoundException;
import com.Ambalaj.Ambalaj.model.CategoryEntity;
import com.Ambalaj.Ambalaj.model.IndustryEntity;
import com.Ambalaj.Ambalaj.repository.CategoryRepository;
import com.Ambalaj.Ambalaj.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryEntity> getCategoriesByIds(List<Long> categoryIds) {
        List<CategoryEntity> categories = categoryRepository.findAllById(categoryIds);
        Set<Long> foundIds = categories.stream().map(CategoryEntity::getId).collect(Collectors.toSet());
        List<Long> missingIds = categoryIds.stream().filter(id -> !foundIds.contains(id)).toList();
        if (!missingIds.isEmpty()) {
            throw new NotFoundException("Categories not found for IDs: " + missingIds);
        }
        return categories;
    }
}
