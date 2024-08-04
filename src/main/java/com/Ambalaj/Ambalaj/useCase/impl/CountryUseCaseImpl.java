package com.Ambalaj.Ambalaj.useCase.impl;

import com.Ambalaj.Ambalaj.dto.CountryDTO;
import com.Ambalaj.Ambalaj.mapper.CountryMapper;
import com.Ambalaj.Ambalaj.model.CountryEntity;
import com.Ambalaj.Ambalaj.service.CountryService;
import com.Ambalaj.Ambalaj.useCase.CountryUseCase;
import lombok.RequiredArgsConstructor;
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
    public List<CountryDTO> getCountryList() {
        List<CountryEntity> countryList = countryService.getCountryList();
        return countryMapper.toListDto(countryList);
    }

    @Override
    public CountryDTO getCountry(Integer countryId) {
        CountryEntity country = countryService.getCountry(countryId);
        return countryMapper.toDto(country);
    }

    @Override
    public CountryDTO updateCountry(CountryDTO countryDTO, Integer countryId) {
        CountryEntity countryEntity = countryMapper.toEntity(countryDTO);
        CountryEntity addedCountry = countryService.updateCountry(countryEntity, countryId);
        return countryMapper.toDto(addedCountry);
    }

    @Override
    public void deleteCountry(Integer countryId) {
        countryService.deleteCountry(countryId);
    }
}
