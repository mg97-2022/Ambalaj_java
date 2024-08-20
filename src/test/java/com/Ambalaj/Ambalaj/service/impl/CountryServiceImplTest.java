package com.Ambalaj.Ambalaj.service.impl;

import com.Ambalaj.Ambalaj.exception.NotFoundException;
import com.Ambalaj.Ambalaj.model.CountryEntity;
import com.Ambalaj.Ambalaj.repository.CountryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CountryServiceImplTest {

    @Mock
    private CountryRepository countryRepository;

    @InjectMocks
    private CountryServiceImpl underTest;

    private CountryEntity mockCountry;

    @BeforeEach
    void setUp() {
        mockCountry = new CountryEntity();
        mockCountry.setId(1);
        mockCountry.setName("test");
    }

    @Test
    void addCountry_shouldReturnAddedCountry() {
        // given
        when(countryRepository.save(mockCountry)).thenReturn(mockCountry);

        // when
        CountryEntity savedCountry = underTest.addCountry(mockCountry);

        // then
        assertThat(savedCountry).isEqualTo(mockCountry);
        verify(countryRepository, times(1)).save(mockCountry);
    }

    @Test
    void addCountry_shouldThrowExceptionWhenCountryNameIsNotUnique() {
        // given
        when(countryRepository.save(mockCountry)).thenThrow(DataIntegrityViolationException.class);

        // when & then
        assertThrows(DataIntegrityViolationException.class, () -> {
            underTest.addCountry(mockCountry);
        });
        verify(countryRepository, times(1)).save(mockCountry);
    }

    @Test
    void getCountryList_shouldReturnListOfCountries() {
        // given
        List<CountryEntity> mockCountryList = Collections.singletonList(mockCountry);
        when(countryRepository.findAll()).thenReturn(mockCountryList);

        // when
        List<CountryEntity> result = underTest.getCountryList();

        // then
        assertThat(result.size()).isEqualTo(1);
        assertThat(result).isEqualTo(mockCountryList);
        verify(countryRepository, times(1)).findAll();
    }

    @Test
    void getCountry_WhenCountryExists_ShouldReturnCountry() {
        // given
        when(countryRepository.findById(mockCountry.getId())).thenReturn(Optional.of(mockCountry));

        // when
        CountryEntity country = underTest.getCountry(mockCountry.getId());

        // then
        assertThat(country).isEqualTo(mockCountry);
        verify(countryRepository, times(1)).findById(mockCountry.getId());
    }

    @Test
    void getCountry_WhenCountryNotExists_ShouldThrowNotFoundException() {
        // given
        int notFoundId = 2;
        when(countryRepository.findById(notFoundId)).thenThrow(
                new NotFoundException("Country not found with ID: " + notFoundId));

        // when & then
        NotFoundException exception = assertThrows(NotFoundException.class, () -> underTest.getCountry(notFoundId));
        assertThat("Country not found with ID: " + notFoundId).isEqualTo(exception.getMessage());
        verify(countryRepository, times(1)).findById(notFoundId);
    }

    @Test
    void updateCountry_WhenCountryExists_ShouldUpdateCountry() {
        // given
        CountryEntity updatedCountry = new CountryEntity();
        updatedCountry.setId(1);
        updatedCountry.setName("Updated country");

        when(countryRepository.findById(updatedCountry.getId())).thenReturn(Optional.of(mockCountry));
        when(countryRepository.save(mockCountry)).thenReturn(mockCountry);

        // when
        CountryEntity result = underTest.updateCountry(updatedCountry, updatedCountry.getId());

        // then
        assertThat("Updated country").isEqualTo(result.getName());
        verify(countryRepository, times(1)).findById(1);
        verify(countryRepository, times(1)).save(mockCountry);
    }

    @Test
    void updateCountry_WhenCountryNotExists_ShouldThrowNotFoundException() {
        // given
        int notFoundId = 2;
        when(countryRepository.findById(notFoundId)).thenThrow(
                new NotFoundException("Country not found with ID: " + notFoundId));

        // when & then
        NotFoundException exception =
                assertThrows(NotFoundException.class, () -> underTest.updateCountry(mockCountry, notFoundId));
        assertThat("Country not found with ID: " + notFoundId).isEqualTo(exception.getMessage());
        verify(countryRepository, times(1)).findById(notFoundId);
        verify(countryRepository, never()).save(any());
    }

    @Test
    void deleteCountry_WhenCountryExists_ShouldDeleteCountry() {
        // given
        when(countryRepository.existsById(mockCountry.getId())).thenReturn(true);

        // when
        underTest.deleteCountry(mockCountry.getId());

        // then
        verify(countryRepository, times(1)).existsById(mockCountry.getId());
        verify(countryRepository, times(1)).deleteById(mockCountry.getId());
    }

    @Test
    void deleteCountry_WhenCountryNotExists_ShouldThrowNotFoundException() {
        // given
        int notFoundId = 2;
        when(countryRepository.existsById(notFoundId)).thenReturn(false);

        // when & then
        NotFoundException exception = assertThrows(NotFoundException.class, () -> underTest.deleteCountry(notFoundId));
        assertThat("Country not found with ID: " + notFoundId).isEqualTo(exception.getMessage());
        verify(countryRepository, times(1)).existsById(notFoundId);
        verify(countryRepository, never()).deleteById(any());
    }
}