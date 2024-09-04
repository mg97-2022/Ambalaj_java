package com.Ambalaj.Ambalaj.service;

import com.Ambalaj.Ambalaj.model.MaterialSpecificationNameEntity;

import java.util.List;

public interface MaterialSpecificationNameService {
    MaterialSpecificationNameEntity addMaterialSpecificationName(
            MaterialSpecificationNameEntity materialSpecificationNameEntity);

    List<MaterialSpecificationNameEntity> getAllMaterialSpecificationName();

    MaterialSpecificationNameEntity getMaterialSpecificationName(Long materialSpecificationNameId);

    MaterialSpecificationNameEntity updateMaterialSpecificationName(
            MaterialSpecificationNameEntity materialSpecificationNameEntity, Long materialSpecificationNameId);

    void deleteMaterialSpecificationName(Long materialSpecificationNameId);
}
