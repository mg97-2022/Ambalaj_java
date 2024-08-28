package com.Ambalaj.Ambalaj.service;

import com.Ambalaj.Ambalaj.model.CategoryEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
    CategoryEntity addCategory(CategoryEntity categoryEntity);

    List<CategoryEntity> getAllCategories(boolean parentOnly);

    Page<CategoryEntity> getCategories(
            Integer page, Integer pageSize, String sortBy, String sortDirection, String search);

    CategoryEntity getCategory(Long categoryId);

    CategoryEntity updateCategory(CategoryEntity categoryEntity, Long categoryId);

    void deleteCategory(Long categoryId);
}
