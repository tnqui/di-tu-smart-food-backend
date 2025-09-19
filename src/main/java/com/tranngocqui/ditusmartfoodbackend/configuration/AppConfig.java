package com.tranngocqui.ditusmartfoodbackend.configuration;

import com.tranngocqui.ditusmartfoodbackend.entity.Permission;
import com.tranngocqui.ditusmartfoodbackend.entity.Role;
import com.tranngocqui.ditusmartfoodbackend.entity.User;
import com.tranngocqui.ditusmartfoodbackend.repository.PermissionRepository;
import com.tranngocqui.ditusmartfoodbackend.repository.RoleRepository;
import com.tranngocqui.ditusmartfoodbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class AppConfig {
    private final PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository, RoleRepository roleRepository, PermissionRepository permissionRepository) {
        return args -> {
            if (userRepository.findByEmail("admin@gmail.com").isEmpty()) {
                Permission permission = Permission.builder()
                        .name("ADMIN")
                        .description("All Permissions")
                        .build();
                permissionRepository.save(permission);

                Role adminRole = roleRepository.findByName("ADMIN")
                        .orElseGet(() -> roleRepository.save(
                                Role.builder().name("ADMIN").permissions(Set.of(permission)).build()
                        ));

                Set<Role> roles = new HashSet<>();
                roles.add(adminRole);

                User user = User.builder()
                        .phone("123456789")
                        .avatarUrl("/")
                        .updatedAt(LocalDateTime.now())
                        .createdAt(LocalDateTime.now())
                        .email("admin@gmail.com")
                        .password(passwordEncoder.encode("123456789aA@"))
                        .isEmailVerified(true)
                        .isPhoneVerified(true)
                        .twoFactorEnabled(false)
                        .avatarUrl("/")
                        .fullName("Admin")
                        .language("English")
                        .lastLoginAt(LocalDateTime.now())
                        .lastLoginIp("127.0.0.1")
                        .enabled(true)
                        .timezone("America/Los_Angeles")
                        .roles(new HashSet<>(roles))
                        .build();
                userRepository.save(user);
                log.warn("admin user has been created with default password: 123aA@, please change it!");
            }
        };
    }
}
