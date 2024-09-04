package com.Ambalaj.Ambalaj.dto;

import com.Ambalaj.Ambalaj.interfaces.Create;
import com.Ambalaj.Ambalaj.interfaces.Update;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class MaterialRequestDTO {
    private Long id;

    @NotBlank(message = "Material name is required.", groups = {Create.class, Update.class})
    @Size(min = 2, max = 100, message = "Material name should be between 2 and 100 characters",
            groups = {Create.class, Update.class})
    private String name;

    @NotBlank(message = "Material name is required.", groups = {Create.class, Update.class})
    private String description;

    @NotNull(message = "Material image is required.", groups = Create.class)
    private MultipartFile image;

    @Valid
    @NotNull(message = "Material specifications are required.", groups = {Create.class, Update.class})
    private List<MaterialSpecificationDTO> specifications;
}
