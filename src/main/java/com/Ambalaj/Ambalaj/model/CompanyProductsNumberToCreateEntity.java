package com.Ambalaj.Ambalaj.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "company_products_number_to_create")
@Data
public class CompanyProductsNumberToCreateEntity {
    @Id
    private Long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyEntity company;

    @Column(nullable = false)
    private Integer productsNumber = 0;
}
