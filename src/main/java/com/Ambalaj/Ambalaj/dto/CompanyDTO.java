package com.Ambalaj.Ambalaj.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class CompanyDTO extends AppUserTypeExtraDetailsDTO {
    private String email;
    private String description;
    private List<String> phoneNumbers;
    private String website;
    private String taxNumber;
    private CityDTO city;
    private String address;
    private List<CategoryDTO> categories;
    private List<IndustryDTO> industries;
}
