package com.zp.framework.file.core.enums;

import cn.hutool.core.util.ArrayUtil;
import com.zp.framework.file.core.client.FileClient;
import com.zp.framework.file.core.client.FileClientConfig;
import com.zp.framework.file.core.client.db.DBFileClient;
import com.zp.framework.file.core.client.db.DBFileClientConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;

/**
 * Author : zhengpanone
 * Date : 2023/11/17 16:27
 * Version : v1.0.0
 * Description: 文件存储器枚举
 */
@Getter
@AllArgsConstructor
public enum FileStorageEnum {
    DB(1, DBFileClientConfig.class, DBFileClient.class),
    ;
    /**
     * 存储器
     */
    private final Integer storage;
    /**
     * 配置类
     */
    private final Class<? extends FileClientConfig> configClass;
    /**
     * 客户端类
     */
    private final Class<? extends FileClient> clientClass;


    public static FileStorageEnum getByStorage(Integer storage) {
        return ArrayUtil.firstMatch(o -> o.getStorage().equals(storage), values());
    }
}
