package com.Ambalaj.Ambalaj.useCase;

import com.Ambalaj.Ambalaj.dto.SizeDTO;

import java.util.List;

public interface SizeUseCase {
    SizeDTO addSize(SizeDTO size);

    List<SizeDTO> getAllSizes();

    SizeDTO getSize(Integer sizeId);

    SizeDTO updateSize(SizeDTO size, Integer sizeId);

    void deleteSize(Integer sizeId);
}
