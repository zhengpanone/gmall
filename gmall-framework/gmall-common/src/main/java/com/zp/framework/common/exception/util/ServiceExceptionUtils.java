package com.zp.framework.common.exception.util;

import com.google.common.annotations.VisibleForTesting;
import com.zp.framework.common.exception.ErrorCode;
import com.zp.framework.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Exception工具类
 *
 * @author zhengpanone
 */
@Slf4j
public class ServiceExceptionUtils {
    private static final ConcurrentMap<Integer, String> MESSAGES = new ConcurrentHashMap<>();

    public static ServiceException exception(ErrorCode errorCode) {
        String messagePattern = MESSAGES.getOrDefault(errorCode.code(), errorCode.message());
        return exception(errorCode.code(), messagePattern);
    }

    public static ServiceException exception(Integer code, String messagePattern, Object... params) {
        String messagee = doFormat(code, messagePattern, params);
        return new ServiceException(code, messagee);

    }

    public static ServiceException exception(ErrorCode errorCode, Object... params) {
        String messagePattern = MESSAGES.getOrDefault(errorCode.code(), errorCode.message());
        return exception(errorCode.code(), messagePattern, params);

    }

    /**
     * 将错误编号对应的消息使用params进行格式化。
     *
     * @param code           错误编号
     * @param messagePattern 消息模版
     * @param params         参数
     * @return 格式化后的提示
     */

    @VisibleForTesting
    public static String doFormat(int code, String messagePattern, Object... params) {
        StringBuilder sb = new StringBuilder(messagePattern.length() + 50);
        int i = 0;
        int j;
        int l;
        for (l = 0; l < params.length; l++) {
            j = messagePattern.indexOf("{}", i);
            if (j == -1) {
                log.error("[doFormat][参数过多：错误码({})|错误内容({})|参数({})]", code, messagePattern, params);
                if (i == 0) {
                    return messagePattern;
                } else {
                    sb.append(messagePattern.substring(i));
                    return sb.toString();
                }
            } else {
                sb.append(messagePattern, i, j);
                sb.append(params[l]);
                i = j + 2;
            }
        }
        if (messagePattern.indexOf("{}", i) != -1) {
            log.error("[doFormat][参数过少:错误码({})|错误内容({})|参数({})]", code, messagePattern, params);
        }
        sb.append(messagePattern.substring(i));
        return sb.toString();
    }

}



