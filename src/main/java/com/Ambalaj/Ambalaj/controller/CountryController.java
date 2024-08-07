package com.Ambalaj.Ambalaj.controller;

import com.Ambalaj.Ambalaj.dto.CountryDTO;
import com.Ambalaj.Ambalaj.useCase.CountryUseCase;
import com.Ambalaj.Ambalaj.dto.ResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/country")
@RequiredArgsConstructor
public class CountryController {
    private final CountryUseCase countryUseCase;

    @PostMapping
    public ResponseEntity<ResponseDTO> addCountry(@Valid @RequestBody CountryDTO countryDTO) {
        CountryDTO addedCountry = countryUseCase.addCountry(countryDTO);
        ResponseDTO response = ResponseDTO.builder().data(addedCountry).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getCountries() {
        List<CountryDTO> countries = countryUseCase.getCountryList();
        return ResponseEntity.ok(ResponseDTO.builder().data(countries).build());
    }

    @GetMapping(path = "/{countryId}")
    public ResponseEntity<ResponseDTO> getCountryDetails(@PathVariable Integer countryId) {
        CountryDTO country = countryUseCase.getCountry(countryId);
        return ResponseEntity.ok(ResponseDTO.builder().data(country).build());
    }

    @PutMapping(path = "/{countryId}")
    public ResponseEntity<ResponseDTO> updateCountry(@Valid @RequestBody CountryDTO countryDTO,
                                                     @PathVariable Integer countryId) {
        CountryDTO updatedCountry = countryUseCase.updateCountry(countryDTO, countryId);
        return ResponseEntity.ok(ResponseDTO.builder().data(updatedCountry).build());
    }

    @DeleteMapping(path = "/{countryId}")
    public ResponseEntity<ResponseDTO> deleteCountry(@PathVariable Integer countryId) {
        countryUseCase.deleteCountry(countryId);
        return ResponseEntity.ok(ResponseDTO.builder().message("Country deleted successfully.").build());
    }
}
