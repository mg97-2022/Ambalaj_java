package com.Ambalaj.Ambalaj.service.impl;

import com.Ambalaj.Ambalaj.exception.NotFoundException;
import com.Ambalaj.Ambalaj.model.CountryEntity;
import com.Ambalaj.Ambalaj.repository.CountryRepository;
import com.Ambalaj.Ambalaj.service.CountryService;
import com.Ambalaj.Ambalaj.utils.JpaFeatures;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    private final JpaFeatures jpaFeatures;

    private Specification<CountryEntity> getSearchSpecification(String search) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.or(
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + search.toLowerCase() + "%"));
    }

    public CountryEntity addCountry(CountryEntity country) {
        return countryRepository.save(country);
    }

    public Page<CountryEntity> getCountries(
            Integer page, Integer pageSize, String sortBy, String sortDirection, String search) {
        Pageable pageable = jpaFeatures.getPaginationWithSort(page, pageSize, sortBy, sortDirection);
        if (search != null && !search.trim().isEmpty()) {
            return countryRepository.findAll(getSearchSpecification(search), pageable);
        }
        return countryRepository.findAll(pageable);
    }

    public CountryEntity getCountry(Integer countryId) {
        return countryRepository.findById(countryId).orElseThrow(() -> new NotFoundException("Country", countryId));
    }

    public CountryEntity updateCountry(CountryEntity updatedCountry, Integer countryId) {
        CountryEntity existingCountry = this.getCountry(countryId);
        existingCountry.setName(updatedCountry.getName());
        return countryRepository.save(existingCountry);
    }

    public void deleteCountry(Integer countryId) {
        if (!countryRepository.existsById(countryId)) throw new NotFoundException("Country", countryId);
        countryRepository.deleteById(countryId);
    }
}
