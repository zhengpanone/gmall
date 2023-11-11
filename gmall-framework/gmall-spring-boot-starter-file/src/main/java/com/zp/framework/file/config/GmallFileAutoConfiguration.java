package com.zp.framework.file.config;

import com.zp.framework.file.core.client.FileClientFactory;
import com.zp.framework.file.core.client.FileClientFactoryImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;

/**
 * Author : zhengpanone
 * Date : 2023/11/17 15:07
 * Version : v1.0.0
 * Description: 文件配置类
 */
@AutoConfiguration
public class GmallFileAutoConfiguration {

    public FileClientFactory fileClientFactory() {
        return new FileClientFactoryImpl();
    }
}
