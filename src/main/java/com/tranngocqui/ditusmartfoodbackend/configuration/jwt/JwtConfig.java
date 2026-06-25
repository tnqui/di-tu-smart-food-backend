package com.tranngocqui.ditusmartfoodbackend.configuration.jwt;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.tranngocqui.ditusmartfoodbackend.configuration.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class JwtConfig {

    private final JwtProperties jwtProperties;

    @Bean
    public SecretKey secretKey() {
        byte[] bytes = jwtProperties.secret().getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(bytes, "HmacSHA512");
    }

    @Bean
    public JwtEncoder jwtEncoder(SecretKey secretKey) {
        return new NimbusJwtEncoder(new ImmutableSecret<>(secretKey));
    }

    @Bean
    public JwtDecoder jwtDecoder(SecretKey secretKey) {
        return NimbusJwtDecoder
                .withSecretKey(secretKey)
                .macAlgorithm(jwtProperties.algorithm())
                .build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();

        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            List<GrantedAuthority> authorities = new ArrayList<>();

            List<String> roles = jwt.getClaimAsStringList("roles");

            if (roles != null) {
                roles.stream()
                        .filter(r -> r != null && !r.isBlank())
                        .forEach(role ->
                                authorities.add(new SimpleGrantedAuthority("ROLE_" + role))
                        );
            }

            List<String> permissions = jwt.getClaimAsStringList("permissions");

            if (permissions != null) {
                permissions.stream()
                        .filter(p -> p != null && !p.isBlank())
                        .forEach(permission ->
                                authorities.add(new SimpleGrantedAuthority(permission))
                        );
            }
            System.out.println(authorities);
            return authorities;
        });

        return converter;
    }

}
