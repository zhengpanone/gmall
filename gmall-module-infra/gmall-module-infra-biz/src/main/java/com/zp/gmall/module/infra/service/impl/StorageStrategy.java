package com.zp.gmall.module.infra.service.impl;

import com.zp.gmall.module.infra.enums.StorageType;
import com.zp.gmall.module.infra.service.IStorageService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Author : zhengpanone
 * Date : 2025/4/10 14:57
 * Version : v1.0.0
 * Description:
 */
@Service
public class StorageStrategy {

    private final Map<StorageType, IStorageService> strategies;

    public StorageStrategy(
            S3StorageServiceImpl s3Service
    ) {
        this.strategies = Map.of(
                StorageType.MINIO, s3Service,
                StorageType.ALIYUN_OSS, s3Service
                //StorageType.QINIU, new QiniuStorageService(qiniuAdapter)
        );
    }

    public IStorageService getService(StorageType type) {
        return strategies.get(type);
    }
}
