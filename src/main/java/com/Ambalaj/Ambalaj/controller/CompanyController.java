package com.Ambalaj.Ambalaj.controller;

import com.Ambalaj.Ambalaj.dto.CityDTO;
import com.Ambalaj.Ambalaj.dto.CompanyDTO;
import com.Ambalaj.Ambalaj.dto.PaginatedDTO;
import com.Ambalaj.Ambalaj.dto.ResponseDTO;
import com.Ambalaj.Ambalaj.useCase.CompanyUseCase;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyUseCase companyUseCase;

    @GetMapping
    public ResponseEntity<ResponseDTO<PaginatedDTO<CompanyDTO>>> getCompanies(
            @RequestParam @Min(0) Integer page,
            @RequestParam(defaultValue = "10", required = false) @Min(1) @Max(100) Integer pageSize,
            @RequestParam(required = false) String search, @RequestParam(required = false) List<Long> cities,
            @RequestParam(required = false) List<Integer> countries,
            @RequestParam(required = false) List<Long> industries, @RequestParam(required = false) Long category) {
        PaginatedDTO<CompanyDTO> companies =
                companyUseCase.getPaginatedCompanies(page, pageSize, search, cities, countries, industries, category);
        return ResponseEntity.ok(ResponseDTO.<PaginatedDTO<CompanyDTO>>builder().data(companies).build());
    }

    @GetMapping("/{companyName}")
    public ResponseEntity<ResponseDTO<CompanyDTO>> getCompany(@PathVariable String companyName) {
        CompanyDTO company = companyUseCase.getCompanyByName(companyName);
        return ResponseEntity.ok(ResponseDTO.<CompanyDTO>builder().data(company).build());
    }
}
