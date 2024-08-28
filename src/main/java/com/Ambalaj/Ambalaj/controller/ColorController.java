package com.Ambalaj.Ambalaj.controller;

import com.Ambalaj.Ambalaj.dto.ColorDTO;
import com.Ambalaj.Ambalaj.dto.ResponseDTO;
import com.Ambalaj.Ambalaj.useCase.ColorUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/color")
@RequiredArgsConstructor
public class ColorController {
    private final ColorUseCase colorUseCase;

    @PostMapping
    public ResponseEntity<ResponseDTO<ColorDTO>> addColor(@Valid @RequestBody ColorDTO colorDTO) {
        ColorDTO addedColor = colorUseCase.addColor(colorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseDTO.<ColorDTO>builder().data(addedColor).build());
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<ColorDTO>>> getAllColors() {
        List<ColorDTO> colors = colorUseCase.getAllColors();
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(ResponseDTO.<List<ColorDTO>>builder().data(colors).build());
    }

    @GetMapping("/{colorId}")
    public ResponseEntity<ResponseDTO<ColorDTO>> getColor(
            @PathVariable Integer colorId) {
        ColorDTO color = colorUseCase.getColor(colorId);
        return ResponseEntity.ok(ResponseDTO.<ColorDTO>builder().data(color).build());
    }

    @PutMapping("/{colorId}")
    public ResponseEntity<ResponseDTO<ColorDTO>> updateColor(
            @Valid @RequestBody ColorDTO colorDto, @PathVariable Integer colorId) {
        ColorDTO updatedColor = colorUseCase.updateColor(colorDto, colorId);
        return ResponseEntity.ok(ResponseDTO.<ColorDTO>builder().data(updatedColor).build());
    }

    @DeleteMapping("/{colorId}")
    public ResponseEntity<Void> deleteColor(@PathVariable Integer colorId) {
        colorUseCase.deleteColor(colorId);
        return ResponseEntity.noContent().build();
    }
}
