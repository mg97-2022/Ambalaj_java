package com.Ambalaj.Ambalaj.service;

import com.Ambalaj.Ambalaj.model.CityEntity;
import org.springframework.data.domain.Page;

public interface CityService {
    CityEntity addCity(CityEntity cityEntity);

    Page<CityEntity> getCities(Integer page, Integer pageSize, String sortBy, String sortDirection, String search);

    CityEntity getCity(Long cityId);

    CityEntity updateCity(CityEntity cityEntity, Long cityId);

    void deleteCity(Long cityId);
}
