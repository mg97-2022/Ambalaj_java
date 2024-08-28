package com.Ambalaj.Ambalaj.useCase;

import com.Ambalaj.Ambalaj.dto.CategoryDTO;
import com.Ambalaj.Ambalaj.dto.PaginatedDTO;

import java.util.List;

public interface CategoryUseCase {
    CategoryDTO addCategory(CategoryDTO category);

    List<CategoryDTO> getAllCategories(boolean parentOnly);

    PaginatedDTO<CategoryDTO> getCategories(
            Integer page, Integer pageSize, String sortBy, String sortDirection, String search);

    CategoryDTO getCategory(Long categoryId);

    CategoryDTO updateCategory(CategoryDTO category, Long categoryId);

    void deleteCategory(Long categoryId);
}
