package com.Ambalaj.Ambalaj.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
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
    private Long productsNumber;
}
