package com.Ambalaj.Ambalaj.service.impl;

import com.Ambalaj.Ambalaj.exception.NotFoundException;
import com.Ambalaj.Ambalaj.model.ColorEntity;
import com.Ambalaj.Ambalaj.repository.ColorRepository;
import com.Ambalaj.Ambalaj.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {
    private final ColorRepository colorRepository;

    public ColorEntity addColor(ColorEntity colorEntity) {
        return colorRepository.save(colorEntity);
    }

    public List<ColorEntity> getAllColors() {
        return colorRepository.findAll();
    }

    public ColorEntity getColor(Integer colorId) {
        return colorRepository.findById(colorId).orElseThrow(() -> new NotFoundException("Color", colorId));
    }

    public ColorEntity updateColor(ColorEntity updatedColor, Integer colorId) {
        ColorEntity existingColor = this.getColor(colorId);
        existingColor.setName(updatedColor.getName());
        existingColor.setHexCode(updatedColor.getHexCode());
        return colorRepository.save(existingColor);
    }

    public void deleteColor(Integer colorId) {
        if (!colorRepository.existsById(colorId)) throw new NotFoundException("Color", colorId);
        colorRepository.deleteById(colorId);
    }
}
