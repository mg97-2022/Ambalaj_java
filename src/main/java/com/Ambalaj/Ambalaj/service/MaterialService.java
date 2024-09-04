package com.Ambalaj.Ambalaj.service;

import com.Ambalaj.Ambalaj.model.MaterialEntity;
import org.springframework.data.domain.Page;

public interface MaterialService {
    MaterialEntity addMaterial(MaterialEntity materialEntity);

    Page<MaterialEntity> getMaterials(
            Integer page, Integer pageSize, String sortBy, String sortDirection,
            String search);

    MaterialEntity getMaterial(Long materialId);

    MaterialEntity updateMaterial(MaterialEntity materialEntity, Long materialId);

    void deleteMaterial(Long materialId);
}
