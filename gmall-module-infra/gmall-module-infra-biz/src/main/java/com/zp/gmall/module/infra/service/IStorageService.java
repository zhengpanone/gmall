package com.zp.gmall.module.infra.service;

import java.io.IOException;
import java.io.InputStream;

/**
 * Author : zhengpanone
 * Date : 2025/4/10 14:51
 * Version : v1.0.0
 * Description:
 */
public interface IStorageService {

    String uploadFile(String bucket, String key, InputStream inputStream) throws IOException;

    String getPresignedUrl(String bucket, String key);
}
