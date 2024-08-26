package com.Ambalaj.Ambalaj.useCase;

import com.Ambalaj.Ambalaj.dto.CityDTO;
import com.Ambalaj.Ambalaj.dto.PaginatedDTO;

public interface CityUseCase {
    CityDTO addCity(CityDTO city);

    PaginatedDTO<CityDTO> getCities(Integer page, Integer pageSize, String sortBy, String sortDirection, String search);

    CityDTO getCity(Long cityId);

    CityDTO updateCity(CityDTO city, Long cityId);

    void deleteCity(Long cityId);
}
