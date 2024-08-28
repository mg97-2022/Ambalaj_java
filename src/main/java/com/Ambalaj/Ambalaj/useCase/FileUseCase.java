package com.Ambalaj.Ambalaj.useCase;

import com.Ambalaj.Ambalaj.dto.FileResourceDTO;
import com.Ambalaj.Ambalaj.dto.UploadFilesRequestDTO;

import java.io.IOException;
import java.util.List;

public interface FileUseCase {
    List<String> uploadFiles(UploadFilesRequestDTO uploadFilesRequestDTO);

    void deleteFiles(List<String> filesPaths);

    FileResourceDTO getFile(String filePath) throws IOException;
}