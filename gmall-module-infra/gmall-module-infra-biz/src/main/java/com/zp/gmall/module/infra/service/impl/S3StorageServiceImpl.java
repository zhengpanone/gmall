package com.zp.gmall.module.infra.service.impl;

import com.zp.gmall.module.infra.service.IStorageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.io.InputStream;

/**
 * Author : zhengpanone
 * Date : 2025/4/10 14:51
 * Version : v1.0.0
 * Description:
 */
@Service
@AllArgsConstructor

public class S3StorageServiceImpl implements IStorageService {
    private final S3Client s3Client;

    @Override
    public String uploadFile(String bucket, String key, InputStream inputStream) throws IOException {
        s3Client.putObject(PutObjectRequest
                        .builder()
                        .bucket(bucket)
                        .key(key).build(),
                RequestBody.fromInputStream(inputStream, inputStream.available()));
        return key;
    }

    @Override
    public String getPresignedUrl(String bucket, String key) {
        return "";
    }
}
