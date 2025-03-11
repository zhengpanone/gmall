package com.zp.gmall.module.infra.config;

import com.zp.gmall.module.infra.properties.StorageProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;

/**
 * Author : zhengpanone
 * Date : 2025/4/10 14:41
 * Version : v1.0.0
 * Description:
 */
@Configuration
public class StorageAutoConfiguration {
    @Bean
    @ConfigurationProperties(prefix = "oss")
    public StorageProperties storageProperties() {
        return new StorageProperties();
    }

    @Bean
    public S3Client s3Client(StorageProperties properties) {
        return switch (properties.getType()) {
            case MINIO -> configurationMinio(properties);
            case ALIYUN_OSS -> configureAliyun(properties);
            case QINIU -> null;
            case AWS_S3 -> null;
        };
    }

    private S3Client configurationMinio(StorageProperties properties) {
        return S3Client.builder()
                .endpointOverride(URI.create(properties.getEndpoint()))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(properties.getAccessKey(), properties.getSecretKey())
                ))
                .region(Region.of(properties.getRegion()))
                // minio必须启用
                .forcePathStyle(true)
                .build();
    }

    private S3Client configureAliyun(StorageProperties props) {
        return S3Client.builder()
                .endpointOverride(URI.create(props.getEndpoint()))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(props.getAccessKey(), props.getSecretKey())
                ))
                .region(Region.of(props.getRegion()))
                .build();
    }



}
