package com.Ambalaj.Ambalaj.useCase.impl;

import com.Ambalaj.Ambalaj.dto.FileResourceDTO;
import com.Ambalaj.Ambalaj.dto.UploadFilesRequestDTO;
import com.Ambalaj.Ambalaj.service.FileService;
import com.Ambalaj.Ambalaj.useCase.FileUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FileUseCaseImpl implements FileUseCase {
    private final FileService fileService;

    @Override
    public List<String> uploadFiles(UploadFilesRequestDTO uploadFilesRequestDTO) {
        return fileService.uploadFiles(uploadFilesRequestDTO);
    }

    @Override
    public void deleteFiles(List<String> filesPaths) {
        fileService.deleteFiles(filesPaths);
    }

    @Override
    public FileResourceDTO getFile(String filePath) throws IOException {
        return fileService.getFile(filePath);
    }
}
