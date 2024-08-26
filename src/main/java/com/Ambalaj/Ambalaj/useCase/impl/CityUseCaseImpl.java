package com.Ambalaj.Ambalaj.useCase.impl;

import com.Ambalaj.Ambalaj.dto.CityDTO;
import com.Ambalaj.Ambalaj.dto.PaginatedDTO;
import com.Ambalaj.Ambalaj.mapper.CityMapper;
import com.Ambalaj.Ambalaj.model.CityEntity;
import com.Ambalaj.Ambalaj.service.CityService;
import com.Ambalaj.Ambalaj.useCase.CityUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CityUseCaseImpl implements CityUseCase {
    private final CityService cityService;
    private final CityMapper cityMapper;

    @Override
    public CityDTO addCity(CityDTO cityDTO) {
        CityEntity cityEntity = cityMapper.toEntity(cityDTO);
        CityEntity addedCity = cityService.addCity(cityEntity);
        return cityMapper.toDto(addedCity);
    }

    @Override
    public PaginatedDTO<CityDTO> getCities(Integer page, Integer pageSize, String sortBy, String sortDirection,
                                           String search, Integer countryId) {
        Page<CityEntity> pageableCityList =
                cityService.getCities(page, pageSize, sortBy, sortDirection, search, countryId);
        return cityMapper.toPaginatedDto(pageableCityList);
    }

    @Override
    public CityDTO getCity(Long cityId) {
        CityEntity city = cityService.getCity(cityId);
        return cityMapper.toDto(city);
    }

    @Override
    public CityDTO updateCity(CityDTO city, Long cityId) {
        CityEntity cityEntity = cityMapper.toEntity(city);
        CityEntity updatedCity = cityService.updateCity(cityEntity, cityId);
        return cityMapper.toDto(updatedCity);
    }

    @Override
    public void deleteCity(Long cityId) {
        cityService.deleteCity(cityId);
    }
}
