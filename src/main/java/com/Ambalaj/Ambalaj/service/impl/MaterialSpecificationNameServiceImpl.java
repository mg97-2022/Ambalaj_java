package com.Ambalaj.Ambalaj.service.impl;

import com.Ambalaj.Ambalaj.exception.NotFoundException;
import com.Ambalaj.Ambalaj.model.MaterialSpecificationNameEntity;
import com.Ambalaj.Ambalaj.repository.MaterialSpecificationNameRepository;
import com.Ambalaj.Ambalaj.service.MaterialSpecificationNameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialSpecificationNameServiceImpl implements MaterialSpecificationNameService {
    private final MaterialSpecificationNameRepository materialSpecificationNameRepository;

    @Override
    public MaterialSpecificationNameEntity addMaterialSpecificationName(
            MaterialSpecificationNameEntity materialSpecificationNameEntity) {
        return materialSpecificationNameRepository.save(materialSpecificationNameEntity);
    }

    @Override
    public List<MaterialSpecificationNameEntity> getAllMaterialSpecificationName() {
        return materialSpecificationNameRepository.findAll();
    }

    @Override
    public MaterialSpecificationNameEntity getMaterialSpecificationName(Long materialSpecificationNameId) {
        return materialSpecificationNameRepository.findById(materialSpecificationNameId).orElseThrow(
                () -> new NotFoundException("Material specification", materialSpecificationNameId));
    }

    @Override
    public MaterialSpecificationNameEntity updateMaterialSpecificationName(
            MaterialSpecificationNameEntity updatedMaterialSpecification, Long materialSpecificationNameId) {
        MaterialSpecificationNameEntity existingMaterialSpecification =
                this.getMaterialSpecificationName(materialSpecificationNameId);
        existingMaterialSpecification.setName(updatedMaterialSpecification.getName());
        existingMaterialSpecification.setType(updatedMaterialSpecification.getType());
        return materialSpecificationNameRepository.save(existingMaterialSpecification);
    }

    @Override
    public void deleteMaterialSpecificationName(Long materialSpecificationNameId) {
        if (!materialSpecificationNameRepository.existsById(materialSpecificationNameId))
            throw new NotFoundException("Material specification", materialSpecificationNameId);
        materialSpecificationNameRepository.deleteById(materialSpecificationNameId);
    }
}
