package com.Ambalaj.Ambalaj.dto;

import com.Ambalaj.Ambalaj.enums.AdminRole;
import lombok.Data;

@Data
public class AdminDTO {
    private String name;
    private String phoneNumber;
    private AdminRole role;
}
