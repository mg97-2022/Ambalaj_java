package com.Ambalaj.Ambalaj.controller;

import com.Ambalaj.Ambalaj.dto.MaterialSpecificationNameDTO;
import com.Ambalaj.Ambalaj.dto.ResponseDTO;
import com.Ambalaj.Ambalaj.useCase.MaterialSpecificationNameUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/material-specification-name")
@RequiredArgsConstructor
public class MaterialSpecificationNameController {
    private final MaterialSpecificationNameUseCase materialSpecificationNameUseCase;

    @PostMapping
    public ResponseEntity<ResponseDTO<MaterialSpecificationNameDTO>> addMaterialSpecificationName(
            @Valid @RequestBody MaterialSpecificationNameDTO materialSpecificationNameDTO) {
        MaterialSpecificationNameDTO addedSpecification =
                materialSpecificationNameUseCase.addMaterialSpecificationName(materialSpecificationNameDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(ResponseDTO.<MaterialSpecificationNameDTO>builder().data(addedSpecification)
                                              .build());
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<MaterialSpecificationNameDTO>>> getAllMaterialSpecificationsNames() {
        List<MaterialSpecificationNameDTO> materialSpecificationNames =
                materialSpecificationNameUseCase.getAllMaterialSpecificationsNames();
        return ResponseEntity.ok(
                ResponseDTO.<List<MaterialSpecificationNameDTO>>builder().data(materialSpecificationNames).build());
    }

    @GetMapping("/{materialSpecificationNameId}")
    public ResponseEntity<ResponseDTO<MaterialSpecificationNameDTO>> getMaterialSpecificationName(
            @PathVariable Long materialSpecificationNameId) {
        MaterialSpecificationNameDTO specificationName =
                materialSpecificationNameUseCase.getMaterialSpecificationName(materialSpecificationNameId);
        return ResponseEntity.ok(ResponseDTO.<MaterialSpecificationNameDTO>builder().data(specificationName).build());
    }

    @PutMapping("/{materialSpecificationNameId}")
    public ResponseEntity<ResponseDTO<MaterialSpecificationNameDTO>> updateMaterialSpecificationName(
            @Valid @RequestBody MaterialSpecificationNameDTO materialSpecificationNameDTO,
            @PathVariable Long materialSpecificationNameId) {
        MaterialSpecificationNameDTO updatedSpecificationName =
                materialSpecificationNameUseCase.updateMaterialSpecificationName(materialSpecificationNameDTO,
                                                                                 materialSpecificationNameId);
        return ResponseEntity.ok(
                ResponseDTO.<MaterialSpecificationNameDTO>builder().data(updatedSpecificationName).build());
    }

    @DeleteMapping("/{materialSpecificationNameId}")
    public ResponseEntity<Void> deleteMaterialSpecificationName(@PathVariable Long materialSpecificationNameId) {
        materialSpecificationNameUseCase.deleteMaterialSpecificationName(materialSpecificationNameId);
        return ResponseEntity.noContent().build();
    }
}
