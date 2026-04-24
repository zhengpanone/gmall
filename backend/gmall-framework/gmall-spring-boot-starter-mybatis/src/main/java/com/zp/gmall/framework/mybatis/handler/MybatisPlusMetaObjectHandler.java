package com.zp.gmall.framework.mybatis.handler;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.util.ClassUtils;

import java.nio.charset.Charset;
import java.time.LocalDateTime;

/**
 * @author : zhengpanone
 * Date : 2026/4/15 20:51
 * Version : v1.0.0
 * Description: mybatis-plus 自动填充
 */
@Slf4j
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {
    /**
     * 插入时自动填充
     *
     * @param metaObject 元对象
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        // 插入时自动填充
        fillValIfNullByName("createTime", LocalDateTime.now(), metaObject, true);
        fillValIfNullByName("updateTime", LocalDateTime.now(), metaObject, true);
        // 更新时设置updater
        String currentUser = "system"; // 实际应从SecurityContext获取
        this.strictUpdateFill(metaObject, "creator", String.class, currentUser);
        this.strictUpdateFill(metaObject, "updater", String.class, currentUser);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 更新时自动填充
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());

        // 更新时设置updater
        String currentUser = "system"; // 实际应从SecurityContext获取
        this.strictUpdateFill(metaObject, "updater", String.class, currentUser);
    }

    /**
     * 填充值，先判断是否有手动设置，优先手动设置的值，例如：job必须手动设置
     * @param fieldName 属性名
     * @param fieldVal 属性值
     * @param metaObject MetaObject
     * @param isCover 是否覆盖原有值,避免更新操作手动入参
     */
    private static void fillValIfNullByName(String fieldName, Object fieldVal, MetaObject metaObject, boolean isCover) {
        // 0. 如果填充值为空
        if (fieldVal == null) {
            return;
        }

        // 1. 没有 set 方法
        if (!metaObject.hasSetter(fieldName)) {
            return;
        }
        // 2. 如果用户有手动设置的值
        Object userSetValue = metaObject.getValue(fieldName);
        String setValueStr = StrUtil.str(userSetValue, Charset.defaultCharset());
        if (StrUtil.isNotBlank(setValueStr) && !isCover) {
            return;
        }
        // 3. field 类型相同时设置
        Class<?> getterType = metaObject.getGetterType(fieldName);
        if (ClassUtils.isAssignableValue(getterType, fieldVal)) {
            metaObject.setValue(fieldName, fieldVal);
        }
    }
}
