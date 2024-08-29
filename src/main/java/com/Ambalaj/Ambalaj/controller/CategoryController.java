package com.Ambalaj.Ambalaj.controller;

import com.Ambalaj.Ambalaj.dto.CategoryDTO;
import com.Ambalaj.Ambalaj.dto.CategoryRequestDTO;
import com.Ambalaj.Ambalaj.dto.PaginatedDTO;
import com.Ambalaj.Ambalaj.dto.ResponseDTO;
import com.Ambalaj.Ambalaj.interfaces.Create;
import com.Ambalaj.Ambalaj.interfaces.Update;
import com.Ambalaj.Ambalaj.useCase.CategoryUseCase;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
@Validated
public class CategoryController {
    private final CategoryUseCase categoryUseCase;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDTO<CategoryDTO>> addCategory(
            @Validated(Create.class) @ModelAttribute CategoryRequestDTO categoryRequestDTO) throws IOException {
        CategoryDTO addedCategory = categoryUseCase.addCategory(categoryRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(ResponseDTO.<CategoryDTO>builder().data(addedCategory).build());
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseDTO<List<CategoryDTO>>> getAllCategories(
            @RequestParam(defaultValue = "false", required = false) boolean parentOnly) {
        List<CategoryDTO> allCategories = categoryUseCase.getAllCategories(parentOnly);
        return ResponseEntity.ok(ResponseDTO.<List<CategoryDTO>>builder().data(allCategories).build());
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<PaginatedDTO<CategoryDTO>>> getCategories(
            @RequestParam @Min(0) Integer page,
            @RequestParam(defaultValue = "10", required = false) @Min(1) @Max(100) Integer pageSize,
            @RequestParam(required = false) String sortBy,
            @RequestParam(defaultValue = "asc", required = false) String sortDirection,
            @RequestParam(required = false) String search) {
        PaginatedDTO<CategoryDTO> categories =
                categoryUseCase.getCategories(page, pageSize, sortBy, sortDirection, search);
        return ResponseEntity.ok(ResponseDTO.<PaginatedDTO<CategoryDTO>>builder().data(categories).build());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<ResponseDTO<CategoryDTO>> getCategory(@PathVariable Long categoryId) {
        CategoryDTO category = categoryUseCase.getCategory(categoryId);
        return ResponseEntity.ok(ResponseDTO.<CategoryDTO>builder().data(category).build());
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<ResponseDTO<CategoryDTO>> updateCategory(
            @Validated(Update.class) @ModelAttribute CategoryRequestDTO categoryRequestDTO,
            @PathVariable Long categoryId) throws IOException {
        CategoryDTO updatedCategory = categoryUseCase.updateCategory(categoryRequestDTO, categoryId);
        return ResponseEntity.ok(ResponseDTO.<CategoryDTO>builder().data(updatedCategory).build());
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
        categoryUseCase.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }
}
