package com.Ambalaj.Ambalaj.service;

import com.Ambalaj.Ambalaj.model.CategoryEntity;

import java.util.List;

public interface CategoryService {
    List<CategoryEntity> getCategoriesByIds(List<Long> categoryIds);
}
