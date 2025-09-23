package com.tranngocqui.ditusmartfoodbackend;

import com.tranngocqui.ditusmartfoodbackend.properties.ZaloPayProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ZaloPayProperties.class)
public class DiTuSmartFoodBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiTuSmartFoodBackendApplication.class, args);
    }

}
