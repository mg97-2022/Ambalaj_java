package com.Ambalaj.Ambalaj.mapper;

import com.Ambalaj.Ambalaj.dto.AdminDTO;
import com.Ambalaj.Ambalaj.model.AdminEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdminMapper {
    @Mapping(target = "name", expression = "java(admin.getFirstName() + ' ' + admin.getLastName())")
    AdminDTO toDto(AdminEntity admin);
}
