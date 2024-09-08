package com.Ambalaj.Ambalaj.model;

import com.Ambalaj.Ambalaj.enums.PlanSubscriptionStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "subscriptions")
@Data
public class SubscriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(updatable = false, name = "user_id", nullable = false)
    private AppUserEntity user;

    @ManyToOne
    @JoinColumn(updatable = false, name = "plan_id", nullable = false)
    private PlanEntity plan;

    @Column(updatable = false, nullable = false)
    private LocalDateTime startDate = LocalDateTime.now();

    @Column(updatable = false, nullable = false)
    private LocalDateTime endDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PlanSubscriptionStatus status = PlanSubscriptionStatus.ACTIVE;
}
