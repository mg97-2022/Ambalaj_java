package com.Ambalaj.Ambalaj.service.impl;

import com.Ambalaj.Ambalaj.model.CountryEntity;
import com.Ambalaj.Ambalaj.repository.CountryRepository;
import com.Ambalaj.Ambalaj.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl extends BaseServiceImpl<CountryEntity, Integer> implements CountryService {
    private final CountryRepository countryRepository;

    @Override
    protected JpaRepository<CountryEntity, Integer> getRepository() {
        return countryRepository;
    }

    @Override
    protected Integer getId(CountryEntity countryEntity) {
        return countryEntity.getId();
    }

    @Override
    public CountryEntity addCountry(CountryEntity country) {
        return addEntity(country);
    }

    @Override
    public List<CountryEntity> getCountryList() {
        return getEntities();
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
