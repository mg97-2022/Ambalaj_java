package com.Ambalaj.Ambalaj.service.impl;

import com.Ambalaj.Ambalaj.exception.CustomException;
import com.Ambalaj.Ambalaj.exception.NotFoundException;
import com.Ambalaj.Ambalaj.model.*;
import com.Ambalaj.Ambalaj.repository.ProductRepository;
import com.Ambalaj.Ambalaj.security.CurrentUser;
import com.Ambalaj.Ambalaj.service.CompanyProductsNumberToCreateService;
import com.Ambalaj.Ambalaj.service.CompanyService;
import com.Ambalaj.Ambalaj.service.ProductService;
import com.Ambalaj.Ambalaj.utils.JpaFeatures;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CompanyService companyService;
    private final CompanyProductsNumberToCreateService companyProductsNumberToCreateService;
    private final JpaFeatures jpaFeatures;

    private Specification<ProductEntity> filterProducts(
            String search, List<Long> industries, List<Long> materials, List<Integer> sizes, List<Integer> colors,
            Long category) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (search != null && !search.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                                                                                "%" + search.toLowerCase() + "%"));
            }

            if (industries != null && !industries.isEmpty()) {
                Join<ProductEntity, CompanyEntity> companyJoin = root.join("company");
                Join<CompanyEntity, IndustryEntity> industryJoin = companyJoin.join("industries");
                predicate = criteriaBuilder.and(predicate, industryJoin.get("id").in(industries));
            }

            if (materials != null && !materials.isEmpty()) {
                Join<ProductEntity, MaterialEntity> materialJoin = root.join("material");
                predicate = criteriaBuilder.and(predicate, materialJoin.get("id").in(materials));
            }

            if (sizes != null && !sizes.isEmpty()) {
                Join<ProductEntity, SizeEntity> sizeJoin = root.join("sizes");
                predicate = criteriaBuilder.and(predicate, sizeJoin.get("id").in(sizes));
            }

            if (colors != null && !colors.isEmpty()) {
                Join<ProductEntity, ColorEntity> colorJoin = root.join("colors");
                predicate = criteriaBuilder.and(predicate, colorJoin.get("id").in(colors));
            }

            if (category != null) {
                Join<ProductEntity, CategoryEntity> categoryJoin = root.join("category");
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(categoryJoin.get("id"), category));
            }

            return predicate;
        };
    }

    private Specification<ProductEntity> filterSimilarProducts(Long category, Long productId) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            Join<ProductEntity, CategoryEntity> categoryJoin = root.join("category");
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(categoryJoin.get("id"), category));

            predicate = criteriaBuilder.and(predicate, criteriaBuilder.notEqual(root.get("id"), productId));

            return predicate;
        };
    }

    @Override
    @Transactional
    public void addProduct(ProductEntity productEntity) {
        AppUserEntity currentLoggedUser = CurrentUser.getCurrentUser();
        CompanyEntity currentLoggedCompany = companyService.findByAppUser(currentLoggedUser);
        CompanyProductsNumberToCreateEntity companyProductsNumberToCreateEntity =
                companyProductsNumberToCreateService.getByCompany(currentLoggedCompany);
        if (companyProductsNumberToCreateEntity.getProductsNumber() == null || companyProductsNumberToCreateEntity.getProductsNumber() <= 0) {
            throw new CustomException("To create more products, please consider upgrading to a new plan",
                                      HttpStatus.BAD_REQUEST);
        }
        productEntity.setCompany(currentLoggedCompany);
        productRepository.save(productEntity);
        companyProductsNumberToCreateEntity.setProductsNumber(
                companyProductsNumberToCreateEntity.getProductsNumber() - 1);
        companyProductsNumberToCreateService.updateCompanyProductsNumberToCreate(companyProductsNumberToCreateEntity);
    }

    @Override
    public Page<ProductEntity> getPaginatedProducts(
            Integer page, Integer pageSize, String search, List<Long> industries, List<Long> materials,
            List<Integer> sizes, List<Integer> colors, Long category) {
        Pageable pageable = jpaFeatures.getPagination(page, pageSize);
        return productRepository.findAll(filterProducts(search, industries, materials, sizes, colors, category),
                                         pageable);
    }

    @Override
    public ProductEntity getProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product", productId));
    }

    @Override
    public Page<ProductEntity> getPaginatedProductSimilarProducts(Integer page, Integer pageSize, Long productId) {
        Pageable pageable = jpaFeatures.getPagination(page, pageSize);
        ProductEntity product = this.getProduct(productId);
        return productRepository.findAll(filterSimilarProducts(product.getCategory().getId(), productId), pageable);
    }
}
