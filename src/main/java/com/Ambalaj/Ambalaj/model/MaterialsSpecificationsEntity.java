package com.Ambalaj.Ambalaj.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "materials_specifications",
        uniqueConstraints = @UniqueConstraint(columnNames = {"material_id", "specification_id"}))
@Data
@EqualsAndHashCode(callSuper = false)
public class MaterialsSpecificationsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "material_id", nullable = false)
    private MaterialEntity material;

    @ManyToOne
    @JoinColumn(name = "specification_id", nullable = false)
    private MaterialSpecificationNameEntity specification;

    @Column(nullable = false)
    private String value;
}
