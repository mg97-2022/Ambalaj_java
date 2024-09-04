package com.Ambalaj.Ambalaj.useCase;

import com.Ambalaj.Ambalaj.dto.PlanDTO;

import java.util.List;

public interface PlanUseCase {
    PlanDTO addPlan(PlanDTO plan);

    List<PlanDTO> getAllPlans(boolean activeOnly);

    PlanDTO getPlan(Integer planId);

    PlanDTO updatePlan(PlanDTO plan, Integer planId);

    void deletePlan(Integer planId);
}
