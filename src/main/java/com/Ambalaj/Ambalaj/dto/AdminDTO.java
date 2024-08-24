package com.Ambalaj.Ambalaj.dto;

import com.Ambalaj.Ambalaj.enums.AdminRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminDTO extends AppUserTypeExtraDetailsDTO {
    private String phoneNumber;
    private AdminRole role;
}
