package com.Ambalaj.Ambalaj.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "company_products_number_to_create")
@Data
public class CompanyProductsNumberToCreateEntity {
    @Id
    private UUID id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private AppUserEntity appUser;

    @Column(nullable = false)
    private Integer productsNumber = 0;
}
