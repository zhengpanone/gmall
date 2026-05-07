package com.zp.gmall.framework.common.util.string;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import org.aspectj.lang.JoinPoint;

/**
 *
 * Project: backend
 * <p>
 * Module: com.zp.gmall.framework.common.util.string
 * <p>
 * Description: 字符串工具类
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-07
 */
public class StrUtils {

    public static String joinMethodArgs(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (ArrayUtil.isEmpty(args)) {
            return "";
        }
        return ArrayUtil.join(args, ",", item -> {
            if (item == null) {
                return "";
            }
            String clazzName = item.getClass().getName();
            if (StrUtil.startWithAny(clazzName, "javax.servlet", "jakarta.servlet", "org.springframework.web")) {
                return "";
            }
            return item;
        });
    }
}
