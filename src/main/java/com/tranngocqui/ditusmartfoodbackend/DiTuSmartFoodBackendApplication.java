package com.tranngocqui.ditusmartfoodbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableMethodSecurity
public class DiTuSmartFoodBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(DiTuSmartFoodBackendApplication.class, args);
    }
}
