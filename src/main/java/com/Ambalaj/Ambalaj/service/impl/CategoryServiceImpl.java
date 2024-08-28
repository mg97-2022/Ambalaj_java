package com.Ambalaj.Ambalaj.service.impl;

import com.Ambalaj.Ambalaj.model.CategoryEntity;
import com.Ambalaj.Ambalaj.repository.CategoryRepository;
import com.Ambalaj.Ambalaj.service.CategoryService;
import com.Ambalaj.Ambalaj.utils.JpaFeatures;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl extends BaseServiceWithPaginationImpl<CategoryEntity, Long> implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final JpaFeatures jpaFeatures;

    @Override
    public JpaRepository<CategoryEntity, Long> getRepository() {
        return categoryRepository;
    }

    @Override
    protected JpaSpecificationExecutor<CategoryEntity> getSpecificationExecutor() {
        return categoryRepository;
    }

    @Override
    protected Specification<CategoryEntity> getSearchSpecification(String search) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (search != null && !search.trim().isEmpty()) {
                String searchPattern = "%" + search.toLowerCase() + "%";
                predicate = criteriaBuilder.and(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), searchPattern));
            }

            return predicate;
        };
    }

    @Override
    public CategoryEntity addCategory(CategoryEntity categoryEntity) {
        return addEntity(categoryEntity);
    }

    @Override
    public List<CategoryEntity> getAllCategories(boolean parentOnly) {
        return parentOnly ? categoryRepository.findByParentCategoryIsNull() : getEntities();
    }

    @Override
    public Page<CategoryEntity> getCategories(
            Integer page, Integer pageSize, String sortBy, String sortDirection, String search) {
        return getPaginatedSortedSearchableEntities(page, pageSize, sortBy, sortDirection, search);
    }

    @Override
    public CategoryEntity getCategory(Long categoryId) {
        return getEntityById(categoryId, "Category");
    }

    @Override
    protected void updateEntityFields(CategoryEntity existingCategory, CategoryEntity updatedCategory) {
        existingCategory.setName(updatedCategory.getName());
        existingCategory.setParentCategory(updatedCategory.getParentCategory());
    }

    @Override
    public CategoryEntity updateCategory(CategoryEntity categoryEntity, Long categoryId) {
        return updateEntity(categoryEntity, categoryId, "Category");
    }

    @Override
    public void deleteCategory(Long categoryId) {
        deleteEntity(categoryId, "Category");
    }
}


