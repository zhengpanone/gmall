package com.zp.framework.common.utils.monitor;

import org.apache.skywalking.apm.toolkit.trace.TraceContext;

/**
 * Author : zhengpanone
 * Date : 2023/11/13 23:32
 * Version : v1.0.0
 * Description: 链接追踪工具类
 * 考虑到每个 starter 都需要用到该工具类，所以放到 common 模块下的 util 包下
 */
public class TracerUtils {
    /**
     * 私有化构造方法
     */
    private TracerUtils() {
    }

    /**
     * 获得链路追踪编号,直接返回SkyWalking的TraceId
     * 如果不存在的话为空字符串
     *
     * @return 链路追踪编号
     */
    public static String getTraceId() {
        return TraceContext.traceId();
    }
}

