package com.Ambalaj.Ambalaj.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUserDetailsDTO {
    private UUID id;
    private String email;
    private String firstName;
    private String lastName;
    private String role;
}
