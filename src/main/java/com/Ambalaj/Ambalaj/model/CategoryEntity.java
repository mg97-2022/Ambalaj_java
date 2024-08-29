package com.Ambalaj.Ambalaj.model;

import com.Ambalaj.Ambalaj.auditing.BaseAuditing;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "categories")
@Data
@EqualsAndHashCode(callSuper = false)
public class CategoryEntity extends BaseAuditing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column(nullable = false)
    private String image;

    @ManyToMany(mappedBy = "categories")
    private List<CompanyEntity> companies;

    @OneToMany(mappedBy = "parentCategory", cascade = {CascadeType.MERGE})
    private List<CategoryEntity> subCategories;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private CategoryEntity parentCategory;
}
