package com.Ambalaj.Ambalaj.useCase;

import com.Ambalaj.Ambalaj.dto.MaterialDTO;
import com.Ambalaj.Ambalaj.dto.MaterialRequestDTO;
import com.Ambalaj.Ambalaj.dto.PaginatedDTO;

import java.io.IOException;
import java.util.List;

public interface MaterialUseCase {
    MaterialDTO addMaterial(MaterialRequestDTO materialRequestDTO) throws IOException;

    List<MaterialDTO> getAllMaterials();

    PaginatedDTO<MaterialDTO> getMaterials(
            Integer page, Integer pageSize, String sortBy, String sortDirection, String search);

    MaterialDTO getMaterial(Long materialId);

    MaterialDTO updateMaterial(MaterialRequestDTO materialRequestDTO, Long materialId) throws IOException;

    void deleteMaterial(Long materialId);
}
