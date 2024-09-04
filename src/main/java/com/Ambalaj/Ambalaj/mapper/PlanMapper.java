package com.Ambalaj.Ambalaj.mapper;

import com.Ambalaj.Ambalaj.dto.PlanDTO;
import com.Ambalaj.Ambalaj.model.PlanEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlanMapper {
    @Mapping(target = "priorityNumber", expression = "java(planEntity.getPriorityNumber().getValue())")
    PlanDTO toDto(PlanEntity planEntity);

    @Mapping(target = "discount", defaultValue = "0.00")
    @Mapping(target = "isPopular", defaultValue = "false")
    @Mapping(target = "status",
            expression = "java(com.Ambalaj.Ambalaj.enums.PlanStatus.ACTIVE)")
    @Mapping(target = "priorityNumber",
            expression = "java(com.Ambalaj.Ambalaj.enums.PlanPriorityNumber.getByValue(planDTO.getPriorityNumber()))")
    PlanEntity toEntity(PlanDTO planDTO);

    List<PlanDTO> toListDto(List<PlanEntity> planEntity);
}
