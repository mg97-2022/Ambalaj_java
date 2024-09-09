package com.Ambalaj.Ambalaj.model;

import com.Ambalaj.Ambalaj.auditing.BaseAuditing;
import com.Ambalaj.Ambalaj.interfaces.AppUserDetails;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "companies")
@Data
@EqualsAndHashCode(callSuper = false)
public class CompanyEntity extends BaseAuditing implements AppUserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id", nullable = false)
    private AppUserEntity appUser;

    @Column(nullable = false, unique = true, length = 150)
    private String name;

    @Column(nullable = false)
    private String description;

    @ElementCollection
    @CollectionTable(name = "company_phone_numbers", joinColumns = @JoinColumn(name = "company_id"))
    @Column(name = "phone_number")
    private List<String> phoneNumbers;

    @Column(unique = true)
    private String website;

    @Column(nullable = false, unique = true)
    private String taxNumber;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "city_id", nullable = false)
    private CityEntity city;

    private String address;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "company_category", joinColumns = @JoinColumn(name = "company_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "category_id", nullable = false),
            uniqueConstraints = @UniqueConstraint(columnNames = {"company_id", "category_id"}))
    private List<CategoryEntity> categories;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "company_industry", joinColumns = @JoinColumn(name = "company_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "industry_id", nullable = false),
            uniqueConstraints = @UniqueConstraint(columnNames = {"company_id", "industry_id"}))
    private List<IndustryEntity> industries;
}