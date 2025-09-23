package com.tranngocqui.ditusmartfoodbackend.configuration;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.UUIDGenerator;
import com.fasterxml.uuid.UUIDType;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UUIDConfig {
    @Bean
    public TimeBasedGenerator uuidGenerator() {
        return Generators.timeBasedGenerator();
    }
}
