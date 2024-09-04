package com.Ambalaj.Ambalaj.service.impl;

import com.Ambalaj.Ambalaj.exception.NotFoundException;
import com.Ambalaj.Ambalaj.model.MaterialEntity;
import com.Ambalaj.Ambalaj.model.MaterialsSpecificationsEntity;
import com.Ambalaj.Ambalaj.repository.MaterialRepository;
import com.Ambalaj.Ambalaj.service.MaterialService;
import com.Ambalaj.Ambalaj.utils.FileUtil;
import com.Ambalaj.Ambalaj.utils.JpaFeatures;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {
    private final MaterialRepository materialRepository;
    private final JpaFeatures jpaFeatures;
    private final FileUtil fileUtil;

    private Specification<MaterialEntity> getSearchSpecification(String search) {
        return (root, _, criteriaBuilder) -> {
            String searchTerm = "%" + search.toLowerCase() + "%";

            Predicate namePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), searchTerm);
            Predicate descriptionPredicate =
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), searchTerm);

            return criteriaBuilder.or(namePredicate, descriptionPredicate);
        };
    }

    @Override
    public MaterialEntity addMaterial(MaterialEntity materialEntity) {
        return materialRepository.save(materialEntity);
    }

    @Override
    public Page<MaterialEntity> getMaterials(
            Integer page, Integer pageSize, String sortBy, String sortDirection, String search) {
        Pageable pageable = jpaFeatures.getPaginationWithSort(page, pageSize, sortBy, sortDirection);
        if (search != null && !search.trim().isEmpty()) {
            return materialRepository.findAll(getSearchSpecification(search), pageable);
        }
        return materialRepository.findAll(pageable);
    }

    @Override
    public MaterialEntity getMaterial(Long materialId) {
        return materialRepository.findById(materialId).orElseThrow(() -> new NotFoundException("Material", materialId));
    }

    @Override
    public MaterialEntity updateMaterial(MaterialEntity updatedMaterial, Long materialId) {
        MaterialEntity existingMaterial = this.getMaterial(materialId);
        String existingMaterialImage = existingMaterial.getImage();
        existingMaterial.setName(updatedMaterial.getName());
        existingMaterial.setDescription(updatedMaterial.getDescription());
        if (updatedMaterial.getImage() != null) existingMaterial.setImage(updatedMaterial.getImage());
        existingMaterial.setSpecifications(updatedMaterial.getSpecifications());

        // Handle specifications update
        List<MaterialsSpecificationsEntity> newSpecifications = updatedMaterial.getSpecifications();

        // Set new specifications and update the material reference
        existingMaterial.getSpecifications().clear(); // Clear existing specifications
        for (MaterialsSpecificationsEntity spec : newSpecifications) {
            spec.setMaterial(existingMaterial); // Set the material reference
            existingMaterial.getSpecifications().add(spec); // Add new specifications
        }

        MaterialEntity savedMaterial = materialRepository.save(existingMaterial);
        if (!savedMaterial.getImage().equals(existingMaterialImage)) {
            fileUtil.deleteFile(existingMaterialImage);
        }
        return savedMaterial;
    }

    @Override
    public void deleteMaterial(Long materialId) {
        MaterialEntity existingMaterial = this.getMaterial(materialId);
        materialRepository.deleteById(materialId);
        fileUtil.deleteFileAsync(existingMaterial.getImage());
    }
}
