package com.Ambalaj.Ambalaj.model;

import com.Ambalaj.Ambalaj.auditing.BaseAuditing;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "countries")
@Data
@EqualsAndHashCode(callSuper = false)
public class CountryEntity extends BaseAuditing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
    private List<CityEntity> cities;
}
