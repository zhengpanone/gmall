package com.zp.gmall.framework.common.exception.util;

import com.google.common.annotations.VisibleForTesting;
import com.zp.gmall.framework.common.exception.ErrorCode;
import com.zp.gmall.framework.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;

/**
 * Author : zhengpanone
 * Date : 2025/4/7 19:37
 * Version : v1.0.0
 * Description:
 */
@Slf4j
public class ServiceExceptionUtil {
    public static ServiceException exception(ErrorCode errorCode) {
        return exception(errorCode.code(), errorCode.msg());
    }

    public static ServiceException exception(Integer code, String messagePattern, Object... params) {
        String message = doFormat(code, messagePattern, params);
        return new ServiceException(code, message);
    }

    // ========== 格式化方法 ==========

    /**
     * 将错误编号对应的消息使用 params 进行格式化。
     *
     * @param code           错误编号
     * @param messagePattern 消息模版
     * @param params         参数
     * @return 格式化后的提示
     */
    @VisibleForTesting
    public static String doFormat(Integer code, String messagePattern, Object... params) {
        StringBuilder stringBuilder = new StringBuilder(messagePattern.length() + 50);
        int i = 0;
        int j;
        int l;
        for (l = 0; l < params.length; l++) {
            j = messagePattern.indexOf('{', i);
            if (j == -1) {
                log.error("[doFormat][参数过多：错误码({})|错误内容({})|参数({})", code, messagePattern, params);
                if (i == 0) {
                    return messagePattern;
                } else {
                    stringBuilder.append(messagePattern.substring(i));
                    return stringBuilder.toString();
                }
            } else {
                stringBuilder.append(messagePattern.substring(i, j));
                stringBuilder.append(params[l]);
                i = j + 2;
            }
        }
        if (messagePattern.indexOf("{}", i) != -1) {
            log.error("[doFormat][参数过少：错误码({})|错误内容({})|参数({})", code, messagePattern, params);
        }
        stringBuilder.append(messagePattern.substring(i));
        return stringBuilder.toString();
    }
}
