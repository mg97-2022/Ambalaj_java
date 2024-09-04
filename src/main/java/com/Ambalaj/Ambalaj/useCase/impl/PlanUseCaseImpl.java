package com.Ambalaj.Ambalaj.useCase.impl;

import com.Ambalaj.Ambalaj.dto.PlanDTO;
import com.Ambalaj.Ambalaj.mapper.PlanMapper;
import com.Ambalaj.Ambalaj.model.PlanEntity;
import com.Ambalaj.Ambalaj.service.PlanService;
import com.Ambalaj.Ambalaj.useCase.PlanUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PlanUseCaseImpl implements PlanUseCase {
    private final PlanService planService;
    private final PlanMapper planMapper;

    @Override
    public PlanDTO addPlan(PlanDTO plan) {
        PlanEntity planEntity = planMapper.toEntity(plan);
        PlanEntity addedPlan = planService.addPlan(planEntity);
        return planMapper.toDto(addedPlan);
    }

    @Override
    public List<PlanDTO> getAllPlans(boolean activeOnly) {
        List<PlanEntity> plans = planService.getAllPlans(activeOnly);
        return planMapper.toListDto(plans);
    }

    @Override
    public PlanDTO getPlan(Integer planId) {
        PlanEntity plan = planService.getPlan(planId);
        return planMapper.toDto(plan);
    }

    @Override
    public PlanDTO updatePlan(PlanDTO plan, Integer planId) {
        PlanEntity planEntity = planMapper.toEntity(plan);
        PlanEntity updatedPlan = planService.updatePlan(planEntity, planId);
        return planMapper.toDto(updatedPlan);
    }

    @Override
    public void deletePlan(Integer planId) {
        planService.deletePlan(planId);
    }
}
