package com.Ambalaj.Ambalaj.model;

import com.Ambalaj.Ambalaj.auditing.BaseAuditing;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "countries")
@Getter
@Setter
public class CountryEntity extends BaseAuditing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;
}
