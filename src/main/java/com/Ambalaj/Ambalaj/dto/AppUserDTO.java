package com.Ambalaj.Ambalaj.dto;

import com.Ambalaj.Ambalaj.enums.AppUserRole;
import lombok.Data;

import java.util.UUID;

@Data
public class AppUserDTO {
    private UUID id;
    private String email;
    private AppUserRole role;
}
