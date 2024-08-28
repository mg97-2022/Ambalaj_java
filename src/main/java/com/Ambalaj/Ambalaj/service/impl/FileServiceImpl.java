package com.Ambalaj.Ambalaj.service.impl;

import com.Ambalaj.Ambalaj.dto.FileResourceDTO;
import com.Ambalaj.Ambalaj.dto.UploadFilesRequestDTO;
import com.Ambalaj.Ambalaj.service.FileService;
import com.Ambalaj.Ambalaj.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileUtil fileUtil;

    @Override
    public List<String> uploadFiles(UploadFilesRequestDTO uploadFilesRequestDTO) {
        return fileUtil.saveFiles(uploadFilesRequestDTO.getFiles(), uploadFilesRequestDTO.getSaveDirectory());
    }

    @Override
    public void deleteFiles(List<String> filesPaths) {
        fileUtil.deleteFilesAsync(filesPaths);
    }

    @Override
    public FileResourceDTO getFile(String filePath) throws IOException {
        Path fileCompletePath = Paths.get(filePath).normalize();
        if (!Files.exists(fileCompletePath) || !Files.isReadable(fileCompletePath)) return null;
        Resource resource = new UrlResource(fileCompletePath.toUri());
        String contentType = Files.probeContentType(fileCompletePath);
        if (contentType == null) contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        return FileResourceDTO.builder().resource(resource).contentType(contentType).build();
    }
}
