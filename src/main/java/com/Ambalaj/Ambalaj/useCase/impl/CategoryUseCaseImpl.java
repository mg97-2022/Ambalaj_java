package com.Ambalaj.Ambalaj.useCase.impl;

import com.Ambalaj.Ambalaj.dto.CategoryDTO;
import com.Ambalaj.Ambalaj.dto.CategoryRequestDTO;
import com.Ambalaj.Ambalaj.dto.PaginatedDTO;
import com.Ambalaj.Ambalaj.mapper.CategoryMapper;
import com.Ambalaj.Ambalaj.model.CategoryEntity;
import com.Ambalaj.Ambalaj.service.CategoryService;
import com.Ambalaj.Ambalaj.useCase.CategoryUseCase;
import com.Ambalaj.Ambalaj.utils.FileUtil;
import com.Ambalaj.Ambalaj.utils.FilesFolders;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryUseCaseImpl implements CategoryUseCase {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;
    private final FileUtil fileUtil;

    @Override
    public CategoryDTO addCategory(CategoryRequestDTO categoryRequestDTO) throws IOException {
        String image = fileUtil.saveFile(categoryRequestDTO.getImage(), FilesFolders.CATEGORIES_FOLDER);
        try {
            CategoryEntity categoryEntity = categoryMapper.toEntityFromCategoryRequestDTO(categoryRequestDTO);
            categoryEntity.setImage(image);
            CategoryEntity addedCategory = categoryService.addCategory(categoryEntity);
            return categoryMapper.toDto(addedCategory);
        } catch (Exception ex) {
            fileUtil.deleteFile(image);
            throw ex;
        }
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
    public CategoryDTO updateCategory(CategoryRequestDTO categoryRequestDTO, Long categoryId) throws IOException {
        String image = fileUtil.saveFile(categoryRequestDTO.getImage(), "images/categories/");
        try {
            CategoryEntity categoryEntity = categoryMapper.toEntityFromCategoryRequestDTO(categoryRequestDTO);
            if (image != null) categoryEntity.setImage(image);
            CategoryEntity updatedCategory = categoryService.updateCategory(categoryEntity, categoryId);
            return categoryMapper.toDto(updatedCategory);
        } catch (Exception ex) {
            fileUtil.deleteFile(image);
            throw ex;
        }
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryService.deleteCategory(categoryId);
    }
}
