package com.Ambalaj.Ambalaj.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDTO {
    private String accessToken;
    private AppUserDTO user;
    private Object extra;
}
