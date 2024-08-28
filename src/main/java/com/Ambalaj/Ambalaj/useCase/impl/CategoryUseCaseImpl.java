package com.Ambalaj.Ambalaj.useCase.impl;

import com.Ambalaj.Ambalaj.dto.CategoryDTO;
import com.Ambalaj.Ambalaj.dto.PaginatedDTO;
import com.Ambalaj.Ambalaj.mapper.CategoryMapper;
import com.Ambalaj.Ambalaj.model.CategoryEntity;
import com.Ambalaj.Ambalaj.service.CategoryService;
import com.Ambalaj.Ambalaj.useCase.CategoryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryUseCaseImpl implements CategoryUseCase {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = categoryMapper.toEntity(categoryDTO);
        CategoryEntity addedCategory = categoryService.addCategory(categoryEntity);
        return categoryMapper.toDto(addedCategory);
    }

    @Override
    public List<CategoryDTO> getAllCategories(boolean parentOnly) {
        List<CategoryEntity> allCategories = categoryService.getAllCategories(parentOnly);
        return categoryMapper.toListDto(allCategories);
    }

    @Override
    public PaginatedDTO<CategoryDTO> getCategories(
            Integer page, Integer pageSize, String sortBy, String sortDirection, String search) {
        Page<CategoryEntity> pageableCategoryList =
                categoryService.getCategories(page, pageSize, sortBy, sortDirection, search);
        return categoryMapper.toPaginatedDto(pageableCategoryList);
    }

    @Override
    public CategoryDTO getCategory(Long categoryId) {
        CategoryEntity category = categoryService.getCategory(categoryId);
        return categoryMapper.toDto(category);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId) {
        CategoryEntity categoryEntity = categoryMapper.toEntity(categoryDTO);
        CategoryEntity updatedCategory = categoryService.updateCategory(categoryEntity, categoryId);
        return categoryMapper.toDto(updatedCategory);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryService.deleteCategory(categoryId);
    }
}
