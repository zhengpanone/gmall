package com.zp.gmall.module.infra.properties;

import com.zp.gmall.module.infra.enums.StorageType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * Author : zhengpanone
 * Date : 2025/4/10 14:37
 * Version : v1.0.0
 * Description:
 */
@Data
@Validated
@ConfigurationProperties(prefix = "storage")
public class StorageProperties {
    // 默认MinIO
    private StorageType type = StorageType.MINIO;
    /**
     * 域名
     */
    @NotBlank
    private String endpoint;



    /**
     * 前缀
     */
    private String prefix;

    /**
     * ACCESS_KEY
     */
    private String accessKey;

    /**
     * SECRET_KEY
     */
    private String secretKey;

    /**
     * 存储空间名
     */
    private String bucketName;

    /**
     * 存储区域
     */
    private String region;

    /**
     * 自定义域名
     */
    private String domain;

    /**
     * 是否https（Y=是,N=否）
     */
    private String isHttps;

    /**
     * 桶权限类型(0private 1public 2custom)
     */
    private String accessPolicy;
}
