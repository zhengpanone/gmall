package com.zp.framework.file.core.client;

/**
 * Author : zhengpanone
 * Date : 2023/11/17 15:22
 * Version : v1.0.0
 
 */
public interface FileClientFactory {
    /**
     * 获得文件客户端
     *
     * @param configId 配置编号
     * @return 文件客户端
     */
    FileClient getFileClient(Long configId);

    /**
     * 创建文件客户端
     * @param configId 配置编号
     * @param storage 存储器的枚举{@link F}
     * @param config
     * @param <Config>
     */
    <Config extends FileClientConfig> void createOrUpdateFileClient(Long configId, Integer storage, Config config);
}
