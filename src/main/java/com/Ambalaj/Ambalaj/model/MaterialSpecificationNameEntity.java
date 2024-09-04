package com.Ambalaj.Ambalaj.model;

import com.Ambalaj.Ambalaj.auditing.BaseAuditing;
import com.Ambalaj.Ambalaj.enums.MaterialSpecificationType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "materials_specifications_names")
@Data
@EqualsAndHashCode(callSuper = false)
public class MaterialSpecificationNameEntity extends BaseAuditing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private MaterialSpecificationType type = MaterialSpecificationType.TEXT;
}
