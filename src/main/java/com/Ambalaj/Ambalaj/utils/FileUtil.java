package com.Ambalaj.Ambalaj.utils;

import com.Ambalaj.Ambalaj.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Component
public class FileUtil {
    private void createDirectoryIfNotExists(String subDirectory) throws IOException {
        Path directoryPath = Paths.get("uploads", subDirectory);
        if (Files.notExists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }
    }

    public String saveFile(MultipartFile file, String subDirectory) throws IOException {
        if (file == null || file.isEmpty() || subDirectory == null) return null;

        createDirectoryIfNotExists(subDirectory);

        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path path = Paths.get("uploads", subDirectory, fileName);

        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        return path.toString();
    }

    public List<String> saveFiles(List<MultipartFile> files, String subDirectory) {
        if (files == null || files.isEmpty()) return List.of();

        List<String> savedFiles = new ArrayList<>();
        return files.stream().map((file) -> {
            try {
                return saveFile(file, subDirectory);
            } catch (IOException e) {
                deleteFilesAsync(savedFiles); // This rolls back to make sure all files are saved together
                throw new CustomException("Failed to save file in directory: " + subDirectory,
                                          HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }).collect(Collectors.toList());
    }

    public void deleteFile(String file) {
        if (file == null) return;

        Path path = Paths.get(file);
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            log.error("Failed to delete file: {}. Exception: {}", path, e.getMessage(), e);
        }
    }

    @Async
    public void deleteFileAsync(String file) {
        deleteFile(file);
    }

    @Async
    public void deleteFilesAsync(List<String> files) {
        for (String file : files) {
            deleteFileAsync(file);
        }
    }
}
