package com.bithumbsystems.bootstarterproject.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.InstanceProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;

@Slf4j
@Configuration
public class AwsConfig {

    @Bean
    public S3AsyncClient getS3Client() throws Exception {
        log.debug("getS3Client called...");
        return S3AsyncClient.builder()
                .region(Region.AP_NORTHEAST_2).build();
    }

}
