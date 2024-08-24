package com.Ambalaj.Ambalaj.mapper;

import com.Ambalaj.Ambalaj.dto.ClientDTO;
import com.Ambalaj.Ambalaj.dto.ClientSignupRequestDTO;
import com.Ambalaj.Ambalaj.model.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AppUserMapper.class})
public interface ClientMapper {
    @Mapping(target = "name", expression = "java(client.getFirstName() + ' ' + client.getLastName())")
    ClientDTO toDto(ClientEntity client);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "appUser", source = "user")
    @Mapping(target = "appUser.type", expression = "java(com.Ambalaj.Ambalaj.enums.AppUserType.CLIENT)")
    ClientEntity toEntityFromSignup(ClientSignupRequestDTO clientSignupRequestDTO);
}
