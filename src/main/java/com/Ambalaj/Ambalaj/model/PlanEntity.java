package com.Ambalaj.Ambalaj.model;

import com.Ambalaj.Ambalaj.auditing.BaseAuditing;
import com.Ambalaj.Ambalaj.enums.PlanPriorityNumber;
import com.Ambalaj.Ambalaj.enums.PlanStatus;
import com.Ambalaj.Ambalaj.enums.WebsiteUserType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Entity
@Table(name = "plans")
@Data
@EqualsAndHashCode(callSuper = true)
public class PlanEntity extends BaseAuditing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal pricePerMonth;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal pricePerYear;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal discount;

    private Boolean isPopular;

    @Enumerated(EnumType.STRING)
    private WebsiteUserType accountType;

    private Integer productsNumberToCreate;

    @Enumerated(EnumType.ORDINAL)
    private PlanPriorityNumber priorityNumber;

    @Enumerated(EnumType.STRING)
    private PlanStatus status;
}
