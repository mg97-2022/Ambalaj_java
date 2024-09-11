package com.Ambalaj.Ambalaj.service.impl;

import com.Ambalaj.Ambalaj.exception.NotFoundException;
import com.Ambalaj.Ambalaj.model.*;
import com.Ambalaj.Ambalaj.repository.CompanyRepository;
import com.Ambalaj.Ambalaj.service.CompanyService;
import com.Ambalaj.Ambalaj.utils.JpaFeatures;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final JpaFeatures jpaFeatures;

    private Specification<CompanyEntity> filterCompanies(
            String search, List<Long> cities, List<Integer> countries, List<Long> industries, Long category) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (search != null && !search.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                                                                                "%" + search.toLowerCase() + "%"));
            }

            if (cities != null && !cities.isEmpty()) {
                Join<CompanyEntity, CityEntity> cityJoin = root.join("city");
                predicate = criteriaBuilder.and(predicate, cityJoin.get("id").in(cities));
            }

            if (countries != null && !countries.isEmpty()) {
                Join<CompanyEntity, CityEntity> cityJoin = root.join("city");
                predicate = criteriaBuilder.and(predicate, cityJoin.get("country").get("id").in(countries));
            }

            if (industries != null && !industries.isEmpty()) {
                Join<CompanyEntity, IndustryEntity> industryJoin = root.join("industries");
                predicate = criteriaBuilder.and(predicate, industryJoin.get("id").in(industries));
            }

            if (category != null) {
                Join<CompanyEntity, CategoryEntity> categoryJoin = root.join("categories");
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(categoryJoin.get("id"), category));
            }

            return predicate;
        };
    }

    @Override
    public void addCompany(CompanyEntity company) {
        companyRepository.save(company);
    }

    @Override
    public CompanyEntity getCompanyByName(String companyName) {
        return companyRepository.findByName(companyName);
    }

    @Override
    public Page<CompanyEntity> getPaginatedCompanies(
            Integer page, Integer pageSize, String search, List<Long> cities, List<Integer> countries,
            List<Long> industries, Long category) {
        Pageable pageable = jpaFeatures.getPagination(page, pageSize);
        return companyRepository.findAll(filterCompanies(search, cities, countries, industries, category), pageable);
    }

    @Override
    public CompanyEntity findByAppUser(AppUserEntity appUser) throws NotFoundException {
        return companyRepository.findByAppUser(appUser).orElseThrow(() -> new NotFoundException("Company"));
    }
}
