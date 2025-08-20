package com.tranngocqui.ditusmartfoodbackend.configuration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.http.apache.ApacheHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;

import java.net.URI;

@Getter
@Setter
@Configuration
@RequiredArgsConstructor
public class CloudflareConfig {

    @Value("${spring.cloudflare.r2.end-point}")
    private String endPoint;

    @Value("${spring.cloudflare.r2.access-key}")
    private String accessKey;

    @Value("${spring.cloudflare.r2.secret-key}")
    private String secretKey;

    @Value("${spring.cloudflare.r2.bucket}")
    private String bucket;

    @Bean
    public S3Client s3Client() {
        S3Configuration serviceConfig = S3Configuration.builder()
                // path-style is required for R2
                .pathStyleAccessEnabled(true)
                // disable AWS4 chunked uploads
                .chunkedEncodingEnabled(false)
                .build();

        return S3Client.builder()
                .httpClientBuilder(ApacheHttpClient.builder())
                .region(Region.of("auto"))
                .endpointOverride(URI.create(this.endPoint))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(
                                this.accessKey,
                                this.secretKey)))
                .serviceConfiguration(serviceConfig)
                .build();
    }
}