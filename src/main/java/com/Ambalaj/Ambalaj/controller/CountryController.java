package com.Ambalaj.Ambalaj.controller;

import com.Ambalaj.Ambalaj.dto.CountryDTO;
import com.Ambalaj.Ambalaj.dto.PaginatedDTO;
import com.Ambalaj.Ambalaj.useCase.CountryUseCase;
import com.Ambalaj.Ambalaj.dto.ResponseDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/country")
@RequiredArgsConstructor
@Validated
public class CountryController {
    private final CountryUseCase countryUseCase;

    @PostMapping
    public ResponseEntity<ResponseDTO<CountryDTO>> addCountry(@Valid @RequestBody CountryDTO countryDTO) {
        CountryDTO addedCountry = countryUseCase.addCountry(countryDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(ResponseDTO.<CountryDTO>builder().data(addedCountry).build());
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<PaginatedDTO<CountryDTO>>> getCountries(
            @RequestParam(defaultValue = "0", required = false) @Min(0) @NotNull Integer page,
            @RequestParam(defaultValue = "10", required = false) @Min(1) @Max(100) @NotNull Integer pageSize,
            @RequestParam(required = false) String sortBy,
            @RequestParam(defaultValue = "asc", required = false) String sortDirection,
            @RequestParam(required = false) String search) {
        PaginatedDTO<CountryDTO> countries = countryUseCase.getCountries(page, pageSize, sortBy, sortDirection, search);
        return ResponseEntity.ok(ResponseDTO.<PaginatedDTO<CountryDTO>>builder().data(countries).build());
    }

    @GetMapping(path = "/{countryId}")
    public ResponseEntity<ResponseDTO<CountryDTO>> getCountry(@PathVariable Integer countryId) {
        CountryDTO country = countryUseCase.getCountry(countryId);
        return ResponseEntity.ok(ResponseDTO.<CountryDTO>builder().data(country).build());
    }

    @PutMapping(path = "/{countryId}")
    public ResponseEntity<ResponseDTO<CountryDTO>> updateCountry(
            @Valid @RequestBody CountryDTO countryDTO, @PathVariable Integer countryId) {
        CountryDTO updatedCountry = countryUseCase.updateCountry(countryDTO, countryId);
        return ResponseEntity.ok(ResponseDTO.<CountryDTO>builder().data(updatedCountry).build());
    }

    @DeleteMapping(path = "/{countryId}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Integer countryId) {
        countryUseCase.deleteCountry(countryId);
        return ResponseEntity.noContent().build();
    }
}
