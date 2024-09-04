package com.Ambalaj.Ambalaj.repository;

import com.Ambalaj.Ambalaj.enums.PlanStatus;
import com.Ambalaj.Ambalaj.model.PlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<PlanEntity, Integer> {
    List<PlanEntity> findByStatus(PlanStatus status);
}
