package com.tranngocqui.ditusmartfoodbackend.configuration;

import com.tranngocqui.ditusmartfoodbackend.entity.User;
import com.tranngocqui.ditusmartfoodbackend.enums.UserRole;
import com.tranngocqui.ditusmartfoodbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;


@Configuration
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        userRepository.save(User.registerUserDemo("ngqui.official@gmail.com", "0925144629", passwordEncoder.encode("28092003"), "Qui", "Tran", Set.of(UserRole.ADMIN, UserRole.CUSTOMER)));


    }
}
