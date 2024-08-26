package com.Ambalaj.Ambalaj.useCase.impl;

import com.Ambalaj.Ambalaj.dto.CountryDTO;
import com.Ambalaj.Ambalaj.dto.PaginatedDTO;
import com.Ambalaj.Ambalaj.mapper.CountryMapper;
import com.Ambalaj.Ambalaj.model.CountryEntity;
import com.Ambalaj.Ambalaj.service.CountryService;
import com.Ambalaj.Ambalaj.useCase.CountryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CountryUseCaseImpl implements CountryUseCase {
    private final CountryService countryService;
    private final CountryMapper countryMapper;

    @Override
    public CountryDTO addCountry(CountryDTO countryDTO) {
        CountryEntity countryEntity = countryMapper.toEntity(countryDTO);
        CountryEntity addedCountry = countryService.addCountry(countryEntity);
        return countryMapper.toDto(addedCountry);
    }

    @Override
    public PaginatedDTO<CountryDTO> getCountryList(Integer page, Integer pageSize, String sortBy, String sortDirection,
                                                   String search) {
        Page<CountryEntity> pageableCountryList =
                countryService.getCountryList(page, pageSize, sortBy, sortDirection, search);
        return countryMapper.toPaginatedDTO(pageableCountryList);
    }

    @Override
    public CountryDTO getCountry(Integer countryId) {
        CountryEntity country = countryService.getCountry(countryId);
        return countryMapper.toDto(country);
    }

    @Override
    public CountryDTO updateCountry(CountryDTO countryDTO, Integer countryId) {
        CountryEntity countryEntity = countryMapper.toEntity(countryDTO);
        CountryEntity updatedCountry = countryService.updateCountry(countryEntity, countryId);
        return countryMapper.toDto(updatedCountry);
    }

    @Override
    public void deleteCountry(Integer countryId) {
        countryService.deleteCountry(countryId);
    }
}
