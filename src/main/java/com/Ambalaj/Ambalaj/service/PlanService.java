package com.Ambalaj.Ambalaj.service;

import com.Ambalaj.Ambalaj.model.PlanEntity;

import java.util.List;

public interface PlanService {
    PlanEntity addPlan(PlanEntity planEntity);

    List<PlanEntity> getAllPlans(boolean activeOnly);

    PlanEntity getPlan(Integer planId);

    PlanEntity updatePlan(PlanEntity planEntity, Integer planId);

    void deletePlan(Integer planId);
}
