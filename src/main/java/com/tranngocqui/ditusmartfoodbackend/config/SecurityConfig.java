package com.tranngocqui.ditusmartfoodbackend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
//    private final UserDetailsService userDetailsService;

//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**", "/css/**", "/js/**", "/images/**")
//                        .permitAll().requestMatchers("/dashboard/**").hasRole("ADMIN").anyRequest().authenticated())
//                .formLogin(form -> form.loginPage("/auth/login").loginProcessingUrl("/auth/login")
//                        .defaultSuccessUrl("/dashboard", true).failureUrl("/auth/login?error=true")
//                        .usernameParameter("email").permitAll())
//                .logout(logout -> logout.logoutUrl("/auth/logout").logoutSuccessUrl("/auth/login?logout=true")
//                        .invalidateHttpSession(true).deleteCookies("JSESSIONID"))
//                .userDetailsService(userDetailsService);
//
//        return http.build();
//    }

    //    Dev
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Tắt CSRF cho đỡ bị chặn POST khi test
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Cho phép tất cả URL
                )
                .formLogin(form -> form.disable())  // Tắt login form
                .logout(logout -> logout.disable()); // Tắt logout

        return http.build();
    }


    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
