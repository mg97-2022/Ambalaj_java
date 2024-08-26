package com.Ambalaj.Ambalaj.service.impl;

import com.Ambalaj.Ambalaj.model.CityEntity;
import com.Ambalaj.Ambalaj.repository.CityRepository;
import com.Ambalaj.Ambalaj.service.CityService;
import com.Ambalaj.Ambalaj.utils.JpaFeatures;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import jakarta.persistence.criteria.Predicate;

@Service
@RequiredArgsConstructor
public class CityServiceImpl extends BaseServiceImpl<CityEntity, Long> implements CityService {
    private final CityRepository cityRepository;
    private final JpaFeatures jpaFeatures;

    @Override
    protected JpaRepository<CityEntity, Long> getRepository() {
        return cityRepository;
    }

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

    @Override
    public CityEntity addCity(CityEntity cityEntity) {
        return addEntity(cityEntity);
    }

    @Override
    public Page<CityEntity> getCities(Integer page, Integer pageSize, String sortBy, String sortDirection,
                                      String search, Integer countryId) {
        Pageable pageable = jpaFeatures.getPaginationWithSort(page, pageSize, sortBy, sortDirection);
        Specification<CityEntity> specification = getCitiesFiltersSpecifications(search, countryId);
        return cityRepository.findAll(specification, pageable);
    }

    @Override
    public CityEntity getCity(Long cityId) {
        return getEntityById(cityId, "City");
    }

    @Override
    protected void updateEntityFields(CityEntity existingCity, CityEntity updatedCity) {
        existingCity.setName(updatedCity.getName());
        existingCity.setCountry(updatedCity.getCountry());
    }

    @Override
    public CityEntity updateCity(CityEntity cityEntity, Long cityId) {
        return updateEntity(cityEntity, cityId, "City");
    }

    @Override
    public void deleteCity(Long cityId) {
        deleteEntity(cityId, "City");
    }
}
