package com.Ambalaj.Ambalaj.service.impl;

import com.Ambalaj.Ambalaj.enums.PlanStatus;
import com.Ambalaj.Ambalaj.enums.WebsiteAccountType;
import com.Ambalaj.Ambalaj.exception.NotFoundException;
import com.Ambalaj.Ambalaj.model.PlanEntity;
import com.Ambalaj.Ambalaj.repository.PlanRepository;
import com.Ambalaj.Ambalaj.service.PlanService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {
    private final PlanRepository planRepository;

    @Override
    public PlanEntity addPlan(PlanEntity planEntity) {
        return planRepository.save(planEntity);
    }

    @Override
    public List<PlanEntity> getAllPlans(boolean activeOnly) {
        return activeOnly ? planRepository.findByStatus(PlanStatus.ACTIVE) : planRepository.findAll();
    }

    @Override
    public PlanEntity getPlan(Integer planId) {
        return planRepository.findById(planId).orElseThrow(() -> new NotFoundException("Plan", planId));
    }

    @Override
    @Transactional
    public PlanEntity updatePlan(PlanEntity updatedPlan, Integer planId) {
        PlanEntity existingPlan = this.getPlan(planId);
        existingPlan.setStatus(PlanStatus.INACTIVE);
        planRepository.save(existingPlan);
        updatedPlan.setId(null); // to make sure that the plan will be added not updated
        return planRepository.save(updatedPlan);
    }

    @Override
    public void deletePlan(Integer planId) {
        PlanEntity existingPlan = this.getPlan(planId);
        existingPlan.setStatus(PlanStatus.DELETED);
        planRepository.save(existingPlan);
    }

    @Override
    public PlanEntity getFreeActivePlan(WebsiteAccountType accountType) {
        return planRepository.findFreeActivePlan(accountType)
                             .orElseThrow(() -> new NotFoundException("Free active"));
    }
}
