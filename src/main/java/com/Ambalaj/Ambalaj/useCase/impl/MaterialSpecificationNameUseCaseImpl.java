package com.Ambalaj.Ambalaj.useCase.impl;

import com.Ambalaj.Ambalaj.dto.MaterialSpecificationNameDTO;
import com.Ambalaj.Ambalaj.mapper.MaterialSpecificationNameMapper;
import com.Ambalaj.Ambalaj.model.MaterialSpecificationNameEntity;
import com.Ambalaj.Ambalaj.service.MaterialSpecificationNameService;
import com.Ambalaj.Ambalaj.useCase.MaterialSpecificationNameUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MaterialSpecificationNameUseCaseImpl implements MaterialSpecificationNameUseCase {
    private final MaterialSpecificationNameService materialSpecificationNameService;
    private final MaterialSpecificationNameMapper materialSpecificationNameMapper;

    @Override
    public MaterialSpecificationNameDTO addMaterialSpecificationName(
            MaterialSpecificationNameDTO specificationName) {
        MaterialSpecificationNameEntity specificationNameEntity =
                materialSpecificationNameMapper.toEntity(specificationName);
        MaterialSpecificationNameEntity addedMaterialSpecificationName =
                materialSpecificationNameService.addMaterialSpecificationName(specificationNameEntity);
        return materialSpecificationNameMapper.toDto(addedMaterialSpecificationName);
    }

    @Override
    public List<MaterialSpecificationNameDTO> getAllMaterialSpecificationsNames() {
        List<MaterialSpecificationNameEntity> specificationNames =
                materialSpecificationNameService.getAllMaterialSpecificationName();
        return materialSpecificationNameMapper.toListDto(specificationNames);
    }

    @Override
    public MaterialSpecificationNameDTO getMaterialSpecificationName(Long materialSpecificationNameId) {
        MaterialSpecificationNameEntity specificationName =
                materialSpecificationNameService.getMaterialSpecificationName(materialSpecificationNameId);
        return materialSpecificationNameMapper.toDto(specificationName);
    }

    @Override
    public MaterialSpecificationNameDTO updateMaterialSpecificationName(
            MaterialSpecificationNameDTO specificationName, Long materialSpecificationNameId) {
        MaterialSpecificationNameEntity specificationNameEntity =
                materialSpecificationNameMapper.toEntity(specificationName);
        MaterialSpecificationNameEntity updatedMaterialSpecificationName =
                materialSpecificationNameService.updateMaterialSpecificationName(specificationNameEntity,
                                                                                 materialSpecificationNameId);
        return materialSpecificationNameMapper.toDto(updatedMaterialSpecificationName);
    }

    @Override
    public void deleteMaterialSpecificationName(Long materialSpecificationNameId) {
        materialSpecificationNameService.deleteMaterialSpecificationName(materialSpecificationNameId);
    }
}
