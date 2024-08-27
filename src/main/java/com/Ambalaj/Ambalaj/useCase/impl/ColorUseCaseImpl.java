package com.Ambalaj.Ambalaj.useCase.impl;

import com.Ambalaj.Ambalaj.dto.ColorDTO;
import com.Ambalaj.Ambalaj.mapper.ColorMapper;
import com.Ambalaj.Ambalaj.model.ColorEntity;
import com.Ambalaj.Ambalaj.service.ColorService;
import com.Ambalaj.Ambalaj.useCase.ColorUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ColorUseCaseImpl implements ColorUseCase {
    private final ColorService colorService;
    private final ColorMapper colorMapper;

    @Override
    public ColorDTO addColor(ColorDTO color) {
        ColorEntity colorEntity = colorMapper.toEntity(color);
        ColorEntity addedColor = colorService.addColor(colorEntity);
        return colorMapper.toDto(addedColor);
    }

    @Override
    public List<ColorDTO> getAllColors() {
        List<ColorEntity> colors = colorService.getAllColors();
        return colorMapper.toListDto(colors);
    }

    @Override
    public ColorDTO getColor(Integer colorId) {
        ColorEntity color = colorService.getColor(colorId);
        return colorMapper.toDto(color);
    }

    @Override
    public ColorDTO updateColor(ColorDTO color, Integer colorId) {
        ColorEntity colorEntity = colorMapper.toEntity(color);
        ColorEntity updatedColor = colorService.updateColor(colorEntity, colorId);
        return colorMapper.toDto(updatedColor);
    }

    @Override
    public void deleteColor(Integer colorId) {
        colorService.deleteColor(colorId);
    }
}
