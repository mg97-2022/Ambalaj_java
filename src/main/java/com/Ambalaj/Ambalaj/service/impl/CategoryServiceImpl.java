package com.Ambalaj.Ambalaj.service.impl;

import com.Ambalaj.Ambalaj.exception.NotFoundException;
import com.Ambalaj.Ambalaj.model.CategoryEntity;
import com.Ambalaj.Ambalaj.repository.CategoryRepository;
import com.Ambalaj.Ambalaj.service.CategoryService;
import com.Ambalaj.Ambalaj.utils.FileUtil;
import com.Ambalaj.Ambalaj.utils.JpaFeatures;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final JpaFeatures jpaFeatures;
    private final FileUtil fileUtil;

    private Specification<CategoryEntity> getSearchSpecification(String search) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + search.toLowerCase() + "%"));
    }

    public CategoryEntity addCategory(CategoryEntity categoryEntity) {
        return categoryRepository.save(categoryEntity);
    }

    public List<CategoryEntity> getAllCategories(boolean parentOnly) {
        return parentOnly ? categoryRepository.findByParentCategoryIsNull() : categoryRepository.findAll();
    }

    public Page<CategoryEntity> getCategories(
            Integer page, Integer pageSize, String sortBy, String sortDirection, String search) {
        Pageable pageable = jpaFeatures.getPaginationWithSort(page, pageSize, sortBy, sortDirection);
        if (search != null && !search.trim().isEmpty()) {
            return categoryRepository.findAll(getSearchSpecification(search), pageable);
        }
        return categoryRepository.findAll(pageable);
    }

    public CategoryEntity getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("Category", categoryId));
    }

    public CategoryEntity updateCategory(CategoryEntity updatedCategory, Long categoryId) {
        CategoryEntity existingCategory = this.getCategory(categoryId);
        String existingCategoryImage = existingCategory.getImage();
        existingCategory.setName(updatedCategory.getName());
        if (updatedCategory.getImage() != null) existingCategory.setImage(updatedCategory.getImage());
        existingCategory.setParentCategory(updatedCategory.getParentCategory());
        CategoryEntity savedCategory = categoryRepository.save(existingCategory);
        if (!savedCategory.getImage().equals(existingCategoryImage)) {
            fileUtil.deleteFile(existingCategoryImage);
        }
        return savedCategory;
    }

    public void deleteCategory(Long categoryId) {
        CategoryEntity existingCategory = this.getCategory(categoryId);
        categoryRepository.deleteById(categoryId);
        fileUtil.deleteFileAsync(existingCategory.getImage());
    }
}


