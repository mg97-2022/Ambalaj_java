package com.Ambalaj.Ambalaj.controller;

import com.Ambalaj.Ambalaj.dto.*;
import com.Ambalaj.Ambalaj.useCase.FileUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@RestController
@RequestMapping("files")
@RequiredArgsConstructor
public class FilesController {
    private final FileUseCase fileUseCase;

    @PostMapping(path = "/upload-files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDTO<List<String>>> uploadFile(
            @Valid @ModelAttribute UploadFilesRequestDTO uploadFilesRequestDTO) {
        List<String> uploadFiles = fileUseCase.uploadFiles(uploadFilesRequestDTO);
        return ResponseEntity.ok(ResponseDTO.<List<String>>builder().data(uploadFiles).build());
    }

    @DeleteMapping("/delete-files")
    public ResponseEntity<Void> deleteFiles(
            @Valid @RequestBody DeleteFilesRequestDTO deleteFilesRequestDTO) {
        fileUseCase.deleteFiles(deleteFilesRequestDTO.getFiles());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/**")
    public ResponseEntity<Resource> getFile(HttpServletRequest request) {
        try {
            String requestUrl = request.getRequestURI();
            String basePath = "/files/";
            String relativePath = requestUrl.substring(basePath.length());
            FileResourceDTO fileResourceDTO = fileUseCase.getFile(relativePath);
            if (fileResourceDTO == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            return ResponseEntity.ok().contentType(MediaType.valueOf(fileResourceDTO.getContentType()))
                                 .body(fileResourceDTO.getResource());
        } catch (MalformedURLException e) {
            return ResponseEntity.notFound().build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
