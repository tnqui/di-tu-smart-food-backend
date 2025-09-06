package com.tranngocqui.ditusmartfoodbackend.configuration;

import com.tranngocqui.ditusmartfoodbackend.entity.Permission;
import com.tranngocqui.ditusmartfoodbackend.entity.User;
import com.tranngocqui.ditusmartfoodbackend.enums.Role;
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

@Configuration
@RequiredArgsConstructor
@Slf4j
public class AppConfig {
    private final PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository, RoleRepository roleRepository, PermissionRepository permissionRepository) {
        return args -> {
            if(userRepository.findByEmail("admin@gmail.com").isEmpty()) {
                Permission permission = Permission.builder()
                        .name("ADMIN")
                        .description("All Permissions")
                        .build();
                permissionRepository.save(permission);

                User user = User.builder()
                        .phone("123456789")
                        .avatarUrl("/")
                        .updatedAt(LocalDateTime.now())
                        .createdAt(LocalDateTime.now())
                        .email("admin@gmail.com")
                        .password(passwordEncoder.encode("123"))
                        .isEmailVerified(true)
                        .isPhoneVerified(true)
                        .twoFactorEnabled(true)
                        .avatarUrl("/")
                        .fullName("Admin")
                        .language("English")
                        .lastLoginAt(LocalDateTime.now())
                        .lastLoginIp("127.0.0.1")
                        .enabled(true)
                        .timezone("America/Los_Angeles")
                        .build();
                userRepository.save(user);
                log.warn("admin user has been created with default password: 123aA@, please change it!");
            }
        };
    }
}
