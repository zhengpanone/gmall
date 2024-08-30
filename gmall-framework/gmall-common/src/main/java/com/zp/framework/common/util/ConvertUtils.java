package com.zp.framework.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 转换工具类
 *
 * @author zhengpanone
 */
public class ConvertUtils {
    private static final Logger logger = LoggerFactory.getLogger(ConvertUtils.class);

    public static <T> T sourceToTarget(Object source, Class<T> target) {
        if (source == null) {
            return null;
        }
        T targetObject = null;
        try {
            // 使用 Constructor 来创建实例
            Constructor<T> constructor = target.getDeclaredConstructor();
            // 确保可以访问构造函数
            constructor.setAccessible(true);
            targetObject = constructor.newInstance();
            // 复制属性
            BeanUtils.copyProperties(source, targetObject);
        } catch (Exception e) {
            logger.error("convert error ", e);
        }

        return targetObject;
    }

    public static <T> List<T> sourceToTarget(Collection<?> sourceList, Class<T> target) {
        if (sourceList == null) {
            return null;
        }

        List<T> targetList = new ArrayList<>(sourceList.size());
        try {
            for (Object source : sourceList) {
                // 使用 Constructor 来创建实例
                Constructor<T> constructor = target.getDeclaredConstructor();
                // 确保可以访问构造函数
                constructor.setAccessible(true);
                T targetObject = constructor.newInstance();
                BeanUtils.copyProperties(source, targetObject);
                targetList.add(targetObject);
            }
        } catch (Exception e) {
            logger.error("convert error ", e);
        }

        return targetList;
    }
}