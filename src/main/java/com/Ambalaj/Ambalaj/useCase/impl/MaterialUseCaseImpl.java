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
import java.util.List;

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
    public List<MaterialDTO> getAllMaterials() {
        List<MaterialEntity> allMaterials = materialService.getAllMaterials();
        return materialMapper.toListDto(allMaterials);
    }

    @Override
    public PaginatedDTO<MaterialDTO> getMaterials(
            Integer page, Integer pageSize, String sortBy, String sortDirection, String search) {
        Page<MaterialEntity> pageableMaterialList =
                materialService.getMaterials(page, pageSize, sortBy, sortDirection, search);
        return materialMapper.toPaginatedDto(pageableMaterialList);
    }

    @Override
    public MaterialDTO getMaterial(Long materialId) {
        MaterialEntity material = materialService.getMaterial(materialId);
        return materialMapper.toDto(material);
    }

    @Override
    public MaterialDTO updateMaterial(MaterialRequestDTO materialRequestDTO, Long materialId) throws IOException {
        String image = fileUtil.saveFile(materialRequestDTO.getImage(), FilesFolders.MATERIALS_FOLDER);
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
