package com.Ambalaj.Ambalaj.useCase;

import com.Ambalaj.Ambalaj.dto.CategoryDTO;
import com.Ambalaj.Ambalaj.dto.CategoryRequestDTO;
import com.Ambalaj.Ambalaj.dto.PaginatedDTO;

import java.io.IOException;
import java.util.List;

public interface CategoryUseCase {
    CategoryDTO addCategory(CategoryRequestDTO categoryRequestDTO) throws IOException;

    List<CategoryDTO> getAllCategories(boolean parentOnly);

    PaginatedDTO<CategoryDTO> getCategories(
            Integer page, Integer pageSize, String sortBy, String sortDirection, String search);

    CategoryDTO getCategory(Long categoryId);

    CategoryDTO updateCategory(CategoryRequestDTO categoryRequestDTO, Long categoryId) throws IOException;

    void deleteCategory(Long categoryId);
}
