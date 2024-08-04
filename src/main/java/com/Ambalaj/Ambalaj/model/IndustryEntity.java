package com.Ambalaj.Ambalaj.model;

import com.Ambalaj.Ambalaj.auditing.BaseAuditing;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "industries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IndustryEntity extends BaseAuditing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "industries")
    private List<CompanyEntity> companies;
}
