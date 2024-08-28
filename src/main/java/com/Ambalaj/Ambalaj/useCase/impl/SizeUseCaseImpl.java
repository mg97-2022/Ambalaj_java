package com.Ambalaj.Ambalaj.useCase.impl;

import com.Ambalaj.Ambalaj.dto.SizeDTO;
import com.Ambalaj.Ambalaj.mapper.SizeMapper;
import com.Ambalaj.Ambalaj.model.SizeEntity;
import com.Ambalaj.Ambalaj.service.SizeService;
import com.Ambalaj.Ambalaj.useCase.SizeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SizeUseCaseImpl implements SizeUseCase {
    private final SizeService sizeService;
    private final SizeMapper sizeMapper;

    @Override
    public SizeDTO addSize(SizeDTO size) {
        SizeEntity sizeEntity = sizeMapper.toEntity(size);
        SizeEntity addedSize = sizeService.addSize(sizeEntity);
        return sizeMapper.toDto(addedSize);
    }

    @Override
    public List<SizeDTO> getAllSizes() {
        List<SizeEntity> sizes = sizeService.getAllSizes();
        return sizeMapper.toListDto(sizes);
    }

    @Override
    public SizeDTO getSize(Integer sizeId) {
        SizeEntity size = sizeService.getSize(sizeId);
        return sizeMapper.toDto(size);
    }

    @Override
    public SizeDTO updateSize(SizeDTO size, Integer sizeId) {
        SizeEntity sizeEntity = sizeMapper.toEntity(size);
        SizeEntity updatedSize = sizeService.updateSize(sizeEntity, sizeId);
        return sizeMapper.toDto(updatedSize);
    }

    @Override
    public void deleteSize(Integer sizeId) {
        sizeService.deleteSize(sizeId);
    }
}
