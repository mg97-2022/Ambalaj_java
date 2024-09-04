package com.Ambalaj.Ambalaj.controller;

import com.Ambalaj.Ambalaj.dto.PlanDTO;
import com.Ambalaj.Ambalaj.dto.ResponseDTO;
import com.Ambalaj.Ambalaj.useCase.PlanUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/plan")
@RequiredArgsConstructor
public class PlanController {
    private final PlanUseCase planUseCase;

    @PostMapping
    public ResponseEntity<ResponseDTO<PlanDTO>> addPlan(@Valid @RequestBody PlanDTO planDTO) {
        PlanDTO addedPlan = planUseCase.addPlan(planDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseDTO.<PlanDTO>builder().data(addedPlan).build());
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<PlanDTO>>> getAllPlans(@RequestParam(required = false) boolean activeOnly) {
        List<PlanDTO> plans = planUseCase.getAllPlans(activeOnly);
        return ResponseEntity.ok(ResponseDTO.<List<PlanDTO>>builder().data(plans).build());
    }

    @GetMapping("/{planId}")
    public ResponseEntity<ResponseDTO<PlanDTO>> getPlan(
            @PathVariable Integer planId) {
        PlanDTO plan = planUseCase.getPlan(planId);
        return ResponseEntity.ok(ResponseDTO.<PlanDTO>builder().data(plan).build());
    }

    @PutMapping("/{planId}")
    public ResponseEntity<ResponseDTO<PlanDTO>> updatePlan(
            @Valid @RequestBody PlanDTO planDto, @PathVariable Integer planId) {
        PlanDTO updatedPlan = planUseCase.updatePlan(planDto, planId);
        return ResponseEntity.ok(ResponseDTO.<PlanDTO>builder().data(updatedPlan).build());
    }

    @DeleteMapping("/{planId}")
    public ResponseEntity<Void> deletePlan(@PathVariable Integer planId) {
        planUseCase.deletePlan(planId);
        return ResponseEntity.noContent().build();
    }
}
