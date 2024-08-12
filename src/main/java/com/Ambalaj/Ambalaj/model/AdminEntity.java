package com.Ambalaj.Ambalaj.model;

import com.Ambalaj.Ambalaj.auditing.BaseAuditing;
import com.Ambalaj.Ambalaj.enums.AdminRole;
import com.Ambalaj.Ambalaj.interfaces.AppUserDetails;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "admins")
@Data
@EqualsAndHashCode(callSuper = false)
public class AdminEntity extends BaseAuditing implements AppUserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id", nullable = false)
    private AppUserEntity appUser;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AdminRole role = AdminRole.ADMIN;
}
