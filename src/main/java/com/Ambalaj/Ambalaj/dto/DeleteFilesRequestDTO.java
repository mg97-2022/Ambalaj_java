package com.Ambalaj.Ambalaj.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class DeleteFilesRequestDTO {
    @NotNull(message = "Files are required.")
    @Size(min = 1, message = "You should provide at least one file to delete")
    private List<String> files;
}
