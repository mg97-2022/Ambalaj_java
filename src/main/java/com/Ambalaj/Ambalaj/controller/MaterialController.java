package com.Ambalaj.Ambalaj.controller;

import com.Ambalaj.Ambalaj.dto.*;
import com.Ambalaj.Ambalaj.interfaces.Create;
import com.Ambalaj.Ambalaj.interfaces.Update;
import com.Ambalaj.Ambalaj.useCase.MaterialUseCase;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("api/v1/material")
@RequiredArgsConstructor
@Validated
public class MaterialController {
    private final MaterialUseCase materialUseCase;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDTO<MaterialDTO>> addMaterial(
            @Validated(Create.class) @ModelAttribute MaterialRequestDTO materialRequestDTO) throws IOException {
        MaterialDTO addedMaterial = materialUseCase.addMaterial(materialRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(ResponseDTO.<MaterialDTO>builder().data(addedMaterial).build());
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<PaginatedDTO<MaterialDTO>>> getMaterials(
            @RequestParam @Min(0) Integer page,
            @RequestParam(defaultValue = "10", required = false) @Min(1) @Max(100) Integer pageSize,
            @RequestParam(required = false) String sortBy,
            @RequestParam(defaultValue = "asc", required = false) String sortDirection,
            @RequestParam(required = false) String search) {
        PaginatedDTO<MaterialDTO> materials =
                materialUseCase.getMaterials(page, pageSize, sortBy, sortDirection, search);
        return ResponseEntity.ok(ResponseDTO.<PaginatedDTO<MaterialDTO>>builder().data(materials).build());
    }

    @GetMapping("/{materialId}")
    public ResponseEntity<ResponseDTO<MaterialDTO>> getMaterial(@PathVariable Long materialId) {
        MaterialDTO material = materialUseCase.getMaterial(materialId);
        return ResponseEntity.ok(ResponseDTO.<MaterialDTO>builder().data(material).build());
    }

    @PutMapping("/{materialId}")
    public ResponseEntity<ResponseDTO<MaterialDTO>> updateMaterial(
            @Validated(Update.class) @ModelAttribute MaterialRequestDTO materialRequestDTO,
            @PathVariable Long materialId) throws IOException {
        MaterialDTO updateMaterial = materialUseCase.updateMaterial(materialRequestDTO, materialId);
        return ResponseEntity.ok(ResponseDTO.<MaterialDTO>builder().data(updateMaterial).build());
    }

    @DeleteMapping("/{materialId}")
    public ResponseEntity<Void> deleteMaterial(@PathVariable Long materialId) {
        materialUseCase.deleteMaterial(materialId);
        return ResponseEntity.noContent().build();
    }
}
