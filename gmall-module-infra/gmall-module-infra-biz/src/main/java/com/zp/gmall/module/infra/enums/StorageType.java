package com.zp.gmall.module.infra.enums;

import lombok.Getter;

/**
 * Author : zhengpanone
 * Date : 2025/4/10 14:36
 * Version : v1.0.0
 * Description:
 */
@Getter
public enum StorageType {
    MINIO("minio"),
    ALIYUN_OSS("aliyun"),
    QINIU("qiniu"),
    AWS_S3("aws");

    private final String value;

    StorageType(String value) {
        this.value = value;
    }
}
