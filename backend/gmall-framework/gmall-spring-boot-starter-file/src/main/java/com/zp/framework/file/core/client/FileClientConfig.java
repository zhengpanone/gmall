package com.zp.framework.file.core.client;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Author : zhengpanone
 * Date : 2023/11/17 15:21
 * Version : v1.0.0
 * Description: 文件客户端的配置
 * 不同实现的客户端，需要不同的配置，通过子类来定义
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
// @JsonTypeInfo 注解的作用，Jackson 多态
// 1. 序列化到时数据库时，增加 @class 属性。
// 2. 反序列化到内存对象时，通过 @class 属性，可以创建出正确的类型
public interface FileClientConfig {
}
