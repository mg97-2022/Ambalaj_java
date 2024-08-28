package com.Ambalaj.Ambalaj.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.core.io.Resource;

@Data
@Builder
public class FileResourceDTO {
    private Resource resource;
    private String contentType;
}
