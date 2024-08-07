package com.Ambalaj.Ambalaj.model;

import com.Ambalaj.Ambalaj.enums.AppUserRole;
import com.Ambalaj.Ambalaj.auditing.BaseAuditing;
import com.Ambalaj.Ambalaj.enums.AppRegisterMethod;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(callSuper = false)
public class AppUserEntity extends BaseAuditing implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false, columnDefinition = "UUID")
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private AppUserRole role;

    private String password;

    private LocalDateTime passwordChangedAt;

    private Boolean locked = false;

    private Boolean enabled = false;

    @Enumerated(EnumType.STRING)
    private AppRegisterMethod appRegisterMethod = AppRegisterMethod.EMAIL;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
