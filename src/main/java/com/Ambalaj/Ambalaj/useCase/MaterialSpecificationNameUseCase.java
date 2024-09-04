package com.Ambalaj.Ambalaj.useCase;

import com.Ambalaj.Ambalaj.dto.MaterialSpecificationNameDTO;

import java.io.IOException;
import java.util.List;

public interface MaterialSpecificationNameUseCase {
    MaterialSpecificationNameDTO addMaterialSpecificationName(
            MaterialSpecificationNameDTO materialSpecificationNameDTO);

    List<MaterialSpecificationNameDTO> getAllMaterialSpecificationsNames();

    MaterialSpecificationNameDTO getMaterialSpecificationName(Long materialSpecificationNameId);

    MaterialSpecificationNameDTO updateMaterialSpecificationName(
            MaterialSpecificationNameDTO materialSpecificationNameDTO, Long materialSpecificationNameId);

    void deleteMaterialSpecificationName(Long materialSpecificationNameId);
}
