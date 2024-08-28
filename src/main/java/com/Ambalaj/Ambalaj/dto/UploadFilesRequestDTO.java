package com.Ambalaj.Ambalaj.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class UploadFilesRequestDTO {
    @NotBlank(message = "Files save directory is required.")
    private String saveDirectory;

    @NotNull(message = "Files are required.")
    @Size(min = 1, max = 20, message = "You must upload between 1 and 20 files")
    private List<MultipartFile> files;
}
