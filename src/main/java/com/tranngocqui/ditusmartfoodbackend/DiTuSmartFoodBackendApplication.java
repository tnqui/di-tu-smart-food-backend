package com.tranngocqui.ditusmartfoodbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class DiTuSmartFoodBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(DiTuSmartFoodBackendApplication.class, args);
    }
}
