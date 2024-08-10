package com.Ambalaj.Ambalaj.mapper;

import com.Ambalaj.Ambalaj.dto.AppUserDTO;
import com.Ambalaj.Ambalaj.dto.SignupAppUserDTO;
import com.Ambalaj.Ambalaj.model.AppUserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppUserMapper {
    AppUserDTO toDto(AppUserEntity appUserEntity);

    AppUserEntity toEntityFromSignup(SignupAppUserDTO signupAppUserDTO);
}
