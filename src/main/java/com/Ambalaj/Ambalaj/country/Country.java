package com.Ambalaj.Ambalaj.country;

import com.Ambalaj.Ambalaj.auditing.BaseAuditing;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "countries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Country extends BaseAuditing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;
}
