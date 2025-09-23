package com.tranngocqui.ditusmartfoodbackend.security;

import com.tranngocqui.ditusmartfoodbackend.service.CustomUserDetailsService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    protected static final String[] PUBLIC_ENDPOINTS = {
            "/test/**",
            "/api/orders/**",
            "/api/test/**",
            "/api/auth/login",
            "/api/auth/dashboard-login",
            "/api/auth/2fa-setup",
            "/api/auth/2fa-confirm",
            "/api/auth/verify-2fa",
            "/api/admin/auth/login",
            "/api/admin/auth/verify-2fa",
            "/api/admin/auth/introspect",
            "/api/admin/auth/register",
            "/api/admin/auth/logout",
            "/api/admin/auth/2fa/setup",
            "/api/admin/auth/2fa/confirm",
            "/api/admin/auth/2fa/confirm",
            "/api/admin/auth/2fa/disable",
            "/api/auth/token",
            "/api/auth/login",
            "/api/auth/register",
            "/api/auth/refresh",
            "/api/delivery-methods",
            "/api/categories/**",
            "/api/menu-items/**",
            "/api/delivery-methods/**",
            "/api/dishes/**",
            "/actuator/health",
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };


    private final CustomUserDetailsService customUserDetailsService;
    private final TwoFactorAuthenticationProvider twoFactorAuthProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final PasswordEncoder passwordEncoder;
    private final CorsConfigurationSource corsConfigurationSource;

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
        daoProvider.setUserDetailsService(customUserDetailsService);
        daoProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(List.of(
                daoProvider,
                twoFactorAuthProvider
        ));
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource))
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth
                                //
//                                .anyRequest().permitAll()
                                ///
                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                                .requestMatchers(PUBLIC_ENDPOINTS).permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/auth/verify-email").permitAll()
                                .requestMatchers("/api/admin/**").hasAuthority("ROLE_ADMIN")
                                .requestMatchers("/api/users/**").hasAuthority("USER")
                                .anyRequest().authenticated()
                )
                .authenticationProvider(twoFactorAuthProvider)
                .authenticationProvider(daoAuthenticationProvider())
//                .addFilterBefore(maintenanceFilter, JwtAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.setContentType("application/json");
                            response.getWriter().write("{\"error\":\"Unauthorized\"}");
                        })
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                            response.setContentType("application/json");
                            response.getWriter().write("{\"error\":\"Access Denied\"}");
                        })
                );

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

}
