package com.tranngocqui.ditusmartfoodbackend.configuration.aws;

import com.tranngocqui.ditusmartfoodbackend.configuration.properties.AwsProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;

@Configuration
@RequiredArgsConstructor
public class AwsSesConfig {

    private final AwsProperties awsProperties;

    @Bean
    public SesClient sesClient() {
        return SesClient.builder()
                .region(Region.of(awsProperties.region()))
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(awsProperties.accessKey(), awsProperties.secretKey())))
                .build();
    }


}
