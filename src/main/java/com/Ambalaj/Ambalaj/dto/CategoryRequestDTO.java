package com.Ambalaj.Ambalaj.dto;

import com.Ambalaj.Ambalaj.interfaces.Create;
import com.Ambalaj.Ambalaj.interfaces.Update;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CategoryRequestDTO {
    private Long id;

    @NotBlank(message = "Category name is required.", groups = {Create.class, Update.class})
    @Size(min = 2, max = 100, message = "Category name should be between 2 and 100 characters",
            groups = {Create.class, Update.class})
    private String name;

    @NotNull(message = "Category image is required.", groups = Create.class)
    private MultipartFile image;

    private CategoryDTO parentCategory;
}
