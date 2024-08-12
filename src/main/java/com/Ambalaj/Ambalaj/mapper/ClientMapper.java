package com.Ambalaj.Ambalaj.mapper;

import com.Ambalaj.Ambalaj.dto.ClientDTO;
import com.Ambalaj.Ambalaj.dto.ClientSignupClientDTO;
import com.Ambalaj.Ambalaj.model.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    @Mapping(target = "name", expression = "java(client.getFirstName() + ' ' + client.getLastName())")
    ClientDTO toDto(ClientEntity client);

    ClientEntity toEntityFromSignup(ClientSignupClientDTO clientSignupClientDTO);
}
