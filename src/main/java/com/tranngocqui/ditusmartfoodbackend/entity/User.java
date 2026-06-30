package com.tranngocqui.ditusmartfoodbackend.entity;

import com.tranngocqui.ditusmartfoodbackend.enums.UserRole;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Getter
@Setter(AccessLevel.PRIVATE)
@Entity
@SuperBuilder
@Where(clause = "deleted = false")
@Table(name = "users")
@NoArgsConstructor
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

    @Enumerated(EnumType.STRING)
    private Set<UserRole> roles;

    @OneToMany
    private Set<Permission> permissions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.addAll(roles.stream().map(role -> new SimpleGrantedAuthority(role.name())).toList());
        authorities.addAll(permissions.stream().map(p -> new SimpleGrantedAuthority(p.getName())).toList());
        return authorities;
    }

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
        enabled = true;
    }

    public boolean canLogin() {
        return emailVerified && enabled && !deleted;
    }

    @Override
    public String getUsername() {
        return "";
    }

    public static User registerUserDemo(String email, String phone, String password, String firstName, String lastName, Set<UserRole> roles) {
        return User.builder()
                .email(email)
                .phone(phone)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .emailVerified(true)
                .enabled(true)
                .loginAttempts(0)
                .lastLoginAt(Instant.now())
                .lastLoginIp("")
                .avatarUrl("")
                .language("")
                .timezone("")
                .twoFactorSecret("")
                .twoFactorEnabled(false)
                .roles(roles)
                .build();
    }

    @Override
    public String toString() {
        return String.format("%s %s", firstName, lastName);
    }

}