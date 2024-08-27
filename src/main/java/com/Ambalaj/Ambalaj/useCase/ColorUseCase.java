package com.Ambalaj.Ambalaj.useCase;

import com.Ambalaj.Ambalaj.dto.ColorDTO;

import java.util.List;

public interface ColorUseCase {
    ColorDTO addColor(ColorDTO color);

    List<ColorDTO> getAllColors();

    ColorDTO getColor(Integer colorId);

    ColorDTO updateColor(ColorDTO color, Integer colorId);

    void deleteColor(Integer colorId);
}
