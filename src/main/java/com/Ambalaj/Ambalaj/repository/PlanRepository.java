package com.Ambalaj.Ambalaj.repository;

import com.Ambalaj.Ambalaj.enums.PlanStatus;
import com.Ambalaj.Ambalaj.enums.WebsiteAccountType;
import com.Ambalaj.Ambalaj.model.PlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlanRepository extends JpaRepository<PlanEntity, Integer> {
    List<PlanEntity> findByStatus(PlanStatus status);

    @Query("SELECT p FROM PlanEntity p WHERE p.status = 'ACTIVE' AND p.name = 'free' AND p.accountType = :accountType")
    Optional<PlanEntity> findFreeActivePlan(@Param("accountType") WebsiteAccountType accountType);
}
