package com.Ambalaj.Ambalaj.service.impl;

import com.Ambalaj.Ambalaj.exception.NotFoundException;
import com.Ambalaj.Ambalaj.model.CityEntity;
import com.Ambalaj.Ambalaj.repository.CityRepository;
import com.Ambalaj.Ambalaj.service.CityService;
import com.Ambalaj.Ambalaj.utils.JpaFeatures;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import jakarta.persistence.criteria.Predicate;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final JpaFeatures jpaFeatures;

    private Specification<CityEntity> getCitiesFiltersSpecifications(String search, Integer countryId) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (search != null && !search.trim().isEmpty()) {
                String searchPattern = "%" + search.toLowerCase() + "%";
                predicate = criteriaBuilder.and(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), searchPattern));
            }

            if (countryId != null) {
                predicate =
                        criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("country").get("id"), countryId));
            }

            return predicate;
        };
    }

    public CityEntity addCity(CityEntity cityEntity) {
        return cityRepository.save(cityEntity);
    }

    public Page<CityEntity> getCities(
            Integer page, Integer pageSize, String sortBy, String sortDirection,
            String search, Integer countryId) {
        Pageable pageable = jpaFeatures.getPaginationWithSort(page, pageSize, sortBy, sortDirection);
        return cityRepository.findAll(getCitiesFiltersSpecifications(search, countryId), pageable);
    }

    public CityEntity getCity(Long cityId) {
        return cityRepository.findById(cityId).orElseThrow(() -> new NotFoundException("City", cityId));
    }

    public CityEntity updateCity(CityEntity updatedCity, Long cityId) {
        CityEntity existingCity = this.getCity(cityId);
        existingCity.setName(updatedCity.getName());
        existingCity.setCountry(updatedCity.getCountry());
        return cityRepository.save(existingCity);
    }

    public void deleteCity(Long cityId) {
        if (!cityRepository.existsById(cityId)) throw new NotFoundException("City", cityId);
        cityRepository.deleteById(cityId);
    }
}
