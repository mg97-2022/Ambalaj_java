package com.Ambalaj.Ambalaj.dto;

import com.Ambalaj.Ambalaj.interfaces.AppUserTypeExtraDetails;
import lombok.Data;

@Data
public class CompanyDTO implements AppUserTypeExtraDetails {
    private String name;
}
