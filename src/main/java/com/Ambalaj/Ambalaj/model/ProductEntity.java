package com.Ambalaj.Ambalaj.model;

import com.Ambalaj.Ambalaj.auditing.BaseAuditing;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Entity
@Table(name = "products")
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductEntity extends BaseAuditing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer minOrder;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyEntity company;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "material_id", nullable = false)
    private MaterialEntity material;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProductImageEntity> images;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "product_size", joinColumns = @JoinColumn(name = "product_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "size_id", nullable = false),
            uniqueConstraints = @UniqueConstraint(columnNames = {"product_id", "size_id"}))
    private List<SizeEntity> sizes;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "product_color", joinColumns = @JoinColumn(name = "product_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "color_id", nullable = false),
            uniqueConstraints = @UniqueConstraint(columnNames = {"product_id", "color_id"}))
    private List<ColorEntity> colors;
}
