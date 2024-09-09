package com.Ambalaj.Ambalaj.controller;

import com.Ambalaj.Ambalaj.dto.CompanyDTO;
import com.Ambalaj.Ambalaj.dto.ResponseDTO;
import com.Ambalaj.Ambalaj.useCase.CompanyUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyUseCase companyUseCase;

    @GetMapping("/{companyName}")
    public ResponseEntity<ResponseDTO<CompanyDTO>> getCompany(@PathVariable String companyName) {
        CompanyDTO company = companyUseCase.getCompanyByName(companyName);
        return ResponseEntity.ok(ResponseDTO.<CompanyDTO>builder().data(company).build());
    }
}
