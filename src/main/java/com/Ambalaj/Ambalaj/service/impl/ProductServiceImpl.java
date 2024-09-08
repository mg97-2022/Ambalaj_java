package com.Ambalaj.Ambalaj.service.impl;

import com.Ambalaj.Ambalaj.exception.CustomException;
import com.Ambalaj.Ambalaj.model.AppUserEntity;
import com.Ambalaj.Ambalaj.model.CompanyEntity;
import com.Ambalaj.Ambalaj.model.CompanyProductsNumberToCreateEntity;
import com.Ambalaj.Ambalaj.model.ProductEntity;
import com.Ambalaj.Ambalaj.repository.ProductRepository;
import com.Ambalaj.Ambalaj.security.CurrentUser;
import com.Ambalaj.Ambalaj.service.CompanyProductsNumberToCreateService;
import com.Ambalaj.Ambalaj.service.CompanyService;
import com.Ambalaj.Ambalaj.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CompanyService companyService;
    private final CompanyProductsNumberToCreateService companyProductsNumberToCreateService;

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
}
