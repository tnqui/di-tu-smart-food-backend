package com.tranngocqui.ditusmartfoodbackend.entity;

import com.tranngocqui.ditusmartfoodbackend.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Getter
@Setter(AccessLevel.PRIVATE)
@Entity
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "deleted = false")
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;

    private String password;

    private String firstName;

    private String lastName;

    private boolean emailVerified;

    private boolean isPhoneVerified;

    private boolean enabled;

    private int loginAttempts;

    private Instant lastLoginAt;

    private String lastLoginIp;

    private String avatarUrl;

    private String language;

    private String timezone;

    private String twoFactorSecret;

    private boolean twoFactorEnabled;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Jwt> jwts;

    @Enumerated(EnumType.STRING)
    private Set<UserRole> roles;

    @OneToMany
    private Set<Permission> permissions;

    public static User clientRegister(String email, String phone, String password, String firstName, String lastName) {
        return User.builder()
                .email(email)
                .phone(phone)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .emailVerified(false)
                .enabled(false)
                .loginAttempts(0)
                .lastLoginAt(Instant.now())
                .lastLoginIp("")
                .avatarUrl("")
                .language("")
                .timezone("")
                .twoFactorSecret("")
                .twoFactorEnabled(false)
                .roles(Set.of(UserRole.CUSTOMER))
                .build();
    }

    public void verifyEmail() {
        emailVerified = true;
        enabled=true;
    }

    public boolean canLogin() {
        return emailVerified && enabled && !deleted;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return "";
    }
}