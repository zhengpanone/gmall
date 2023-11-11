package com.zp.framework.file.core.client;

import com.zp.framework.file.core.client.FileClient;
import com.zp.framework.file.core.client.FileClientConfig;
import com.zp.framework.file.core.client.FileClientFactory;

/**
 * Author : zhengpanone
 * Date : 2023/11/17 16:22
 * Version : v1.0.0
 * Description: 文件客户端的工厂实现类
 */
public class FileClientFactoryImpl  implements FileClientFactory {
    /**
     * 获得文件客户端
     *
     * @param configId 配置编号
     * @return 文件客户端
     */
    @Override
    public FileClient getFileClient(Long configId) {
        return null;
    }

    @Override
    public <Config extends FileClientConfig> void createOrUpdateFileClient(Long configId, Integer storage, Config config) {

    }
}
