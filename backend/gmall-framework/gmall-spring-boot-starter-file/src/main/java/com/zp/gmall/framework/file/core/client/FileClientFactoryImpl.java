package com.zp.gmall.framework.file.core.client;

import com.fasterxml.jackson.core.PrettyPrinter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Author : zhengpanone
 * Date : 2023/11/17 16:22
 * Version : v1.0.0
 * Description: 文件客户端的工厂实现类
 */
@Slf4j
public class FileClientFactoryImpl implements FileClientFactory {

    /**
     * 文件客户端的map
     */
    private final Map<String, AbstractFileClient<?>> fileClientMap = new ConcurrentHashMap<>();

    /**
     * 获得文件客户端
     *
     * @param configId 配置编号
     * @return 文件客户端
     */
    @Override
    public FileClient getFileClient(String configId) {
        AbstractFileClient<?> fileClient = fileClientMap.get(configId);
        if (fileClient == null) {
            log.error("文件客户端不存在，configId:{}", configId);
        }
        return fileClient;
    }

    @Override
    public <Config extends FileClientConfig> void createOrUpdateFileClient(String configId, Integer storage, Config config) {
        AbstractFileClient<Config> client = (AbstractFileClient<Config>) fileClientMap.get(configId);
        if (client == null) {
            client = this.createFileClient(configId, storage, config);
            client.init();
            fileClientMap.put(configId, client);
        } else {
            client.refresh(config);
        }
    }

    private <Config extends FileClientConfig> AbstractFileClient<Config> createFileClient(String configId, Integer storage, Config config) {
        return null;
    }
}
