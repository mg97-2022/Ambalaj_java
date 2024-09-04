package com.Ambalaj.Ambalaj.useCase.impl;

import com.Ambalaj.Ambalaj.dto.MaterialDTO;
import com.Ambalaj.Ambalaj.dto.MaterialRequestDTO;
import com.Ambalaj.Ambalaj.dto.PaginatedDTO;
import com.Ambalaj.Ambalaj.mapper.MaterialMapper;
import com.Ambalaj.Ambalaj.model.MaterialEntity;
import com.Ambalaj.Ambalaj.service.MaterialService;
import com.Ambalaj.Ambalaj.useCase.MaterialUseCase;
import com.Ambalaj.Ambalaj.utils.FileUtil;
import com.Ambalaj.Ambalaj.utils.FilesFolders;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class MaterialUseCaseImpl implements MaterialUseCase {
    private final MaterialService materialService;
    private final MaterialMapper materialMapper;
    private final FileUtil fileUtil;

    @Override
    public MaterialDTO addMaterial(MaterialRequestDTO materialRequestDTO) throws IOException {
        String image = fileUtil.saveFile(materialRequestDTO.getImage(), FilesFolders.MATERIALS_FOLDER);
        try {
            MaterialEntity materialEntity = materialMapper.toEntityFromMaterialRequestDTO(materialRequestDTO);
            materialEntity.getSpecifications().forEach(specification -> {
                specification.setMaterial(materialEntity);
            });
            materialEntity.setImage(image);
            MaterialEntity addedMaterial = materialService.addMaterial(materialEntity);
            return materialMapper.toDto(addedMaterial);
        } catch (Exception ex) {
            fileUtil.deleteFile(image);
            throw ex;
        }
    }

    @Override
    public PaginatedDTO<MaterialDTO> getMaterials(
            Integer page, Integer pageSize, String sortBy, String sortDirection, String search) {
        Page<MaterialEntity> pageableCategoryList =
                materialService.getMaterials(page, pageSize, sortBy, sortDirection, search);
        return materialMapper.toPaginatedDto(pageableCategoryList);
    }

    @Override
    public MaterialDTO getMaterial(Long materialId) {
        MaterialEntity category = materialService.getMaterial(materialId);
        return materialMapper.toDto(category);
    }

    @Override
    public MaterialDTO updateMaterial(MaterialRequestDTO materialRequestDTO, Long materialId) throws IOException {
        String image = fileUtil.saveFile(materialRequestDTO.getImage(), "images/categories/");
        try {
            MaterialEntity materialEntity = materialMapper.toEntityFromMaterialRequestDTO(materialRequestDTO);
            if (image != null) materialEntity.setImage(image);
//            materialEntity.setId(materialId);
//            materialEntity.getSpecifications().forEach(specification -> {
//                specification.setMaterial(materialEntity);
//            });
            MaterialEntity updatedMaterial = materialService.updateMaterial(materialEntity, materialId);
            return materialMapper.toDto(updatedMaterial);
        } catch (Exception ex) {
            fileUtil.deleteFile(image);
            throw ex;
        }
    }

    @Override
    public void deleteMaterial(Long materialId) {
        materialService.deleteMaterial(materialId);
    }
}
