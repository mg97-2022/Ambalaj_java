package com.Ambalaj.Ambalaj.dto;

import com.Ambalaj.Ambalaj.enums.AdminRole;
import com.Ambalaj.Ambalaj.interfaces.AppUserTypeExtraDetails;
import lombok.Data;

@Data
public class AdminDTO implements AppUserTypeExtraDetails {
    private String name;
    private String phoneNumber;
    private AdminRole role;
}
