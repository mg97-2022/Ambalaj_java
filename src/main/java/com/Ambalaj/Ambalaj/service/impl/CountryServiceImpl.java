package com.Ambalaj.Ambalaj.service.impl;

import com.Ambalaj.Ambalaj.model.CountryEntity;
import com.Ambalaj.Ambalaj.repository.CountryRepository;
import com.Ambalaj.Ambalaj.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl extends BaseServiceWithPaginationImpl<CountryEntity, Integer> implements CountryService {
    private final CountryRepository countryRepository;

    @Override
    protected JpaRepository<CountryEntity, Integer> getRepository() {
        return countryRepository;
    }

    protected JpaSpecificationExecutor<CountryEntity> getSpecificationExecutor() {
        return countryRepository;
    }

    @Override
    protected Specification<CountryEntity> getSearchSpecification(String search) {
        return (root, query, criteriaBuilder) -> {
            String searchPattern = "%" + search.toLowerCase() + "%";
            return criteriaBuilder.or(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), searchPattern));
        };
    }

    @Override
    public CountryEntity addCountry(CountryEntity country) {
        return addEntity(country);
    }

    @Override
    public Page<CountryEntity> getCountries(Integer page, Integer pageSize, String sortBy, String sortDirection,
                                            String search) {
        return getPaginatedSortedSearchableEntities(page, pageSize, sortBy, sortDirection, search);
    }

    @Override
    public CountryEntity getCountry(Integer countryId) {
        return getEntityById(countryId, "Country");
    }

    @Override
    protected void updateEntityFields(CountryEntity existingCountry, CountryEntity updatedCountry) {
        existingCountry.setName(updatedCountry.getName());
    }

    @Override
    public CountryEntity updateCountry(CountryEntity countryEntity, Integer countryId) {
        return updateEntity(countryEntity, countryId, "Country");
    }

    @Override
    public void deleteCountry(Integer countryId) {
        deleteEntity(countryId, "Country");
    }
}
