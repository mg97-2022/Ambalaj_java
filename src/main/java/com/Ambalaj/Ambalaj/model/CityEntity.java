package com.Ambalaj.Ambalaj.model;

import com.Ambalaj.Ambalaj.auditing.BaseAuditing;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "cities", uniqueConstraints = @UniqueConstraint(columnNames = {"country_id", "name"}))
@Data
@EqualsAndHashCode(callSuper = false)
public class CityEntity extends BaseAuditing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "country_id", nullable = false)
    private CountryEntity country;
}
