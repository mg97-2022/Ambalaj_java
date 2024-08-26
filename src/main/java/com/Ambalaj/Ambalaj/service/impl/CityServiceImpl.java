package com.Ambalaj.Ambalaj.service.impl;

import com.Ambalaj.Ambalaj.model.CityEntity;
import com.Ambalaj.Ambalaj.repository.CityRepository;
import com.Ambalaj.Ambalaj.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityServiceImpl extends BaseServiceWithPaginationImpl<CityEntity, Long> implements CityService {
    private final CityRepository cityRepository;

    @Override
    protected JpaRepository<CityEntity, Long> getRepository() {
        return cityRepository;
    }

    @Override
    protected JpaSpecificationExecutor<CityEntity> getSpecificationExecutor() {
        return cityRepository;
    }

    @Override
    public CityEntity addCity(CityEntity cityEntity) {
        return addEntity(cityEntity);
    }

    @Override
    protected Specification<CityEntity> getSearchSpecification(String search) {
        return (root, query, criteriaBuilder) -> {
            String searchPattern = "%" + search.toLowerCase() + "%";
            return criteriaBuilder.or(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), searchPattern));
        };
    }

    @Override
    public Page<CityEntity> getCities(Integer page, Integer pageSize, String sortBy, String sortDirection,
                                      String search) {
        return getPaginatedSortedSearchableEntities(page, pageSize, sortBy, sortDirection, search);
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
