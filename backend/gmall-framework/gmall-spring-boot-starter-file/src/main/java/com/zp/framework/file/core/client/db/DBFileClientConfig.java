package com.zp.framework.file.core.client.db;

import com.zp.framework.file.core.client.FileClientConfig;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

/**
 * Author : zhengpanone
 * Date : 2023/11/17 16:33
 * Version : v1.0.0
 * Description: 基于DB存储的文件客户端的配置类
 */
@Data
public class DBFileClientConfig implements FileClientConfig {
    /**
     * 自定义域名
     */
    @NotEmpty(message = "domain不能为空")
    @URL(message = "domain必须是URL格式")
    private String domain;
}
