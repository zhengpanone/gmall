package com.zp.framework.core.service;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Author : zhengpanone
 * Date : 2023/12/17 12:29
 * Version : v1.0.0
 * Description: 操作日志
 */
@Data
public class OperateLog {
    /**
     * 链路追踪编号
     */
    private String traceId;
    /**
     * 用户编号
     */
    private String userId;
    /**
     * 用户类型
     */
    private Integer userType;
    /**
     * 操作模块
     */
    private String module;
    /**
     * 操作名
     */
    private String name;
    /**
     * 操作分类
     */
    private Integer type;
    /**
     * 操作明细
     */
    private String content;
    /**
     * 扩展字段
     */
    private Map<String, Object> exts;

    /**
     * 请求方法名
     */
    private String requestMethod;
    /**
     * 请求地址
     */
    private String requestUrl;
    /**
     * 用户IP
     */
    private String userIp;
    /**
     * 浏览器UserAgent
     */
    private String userAgent;
    /**
     * Java方法名
     */
    private String javaMethod;
    /**
     * Java方法参数
     */
    private String javaMethodArgs;
    /**
     * 开始时间
     */
    private LocalDateTime startTime;
    /**
     * 执行时长，单位：毫秒
     */

    private Integer duration;
    /**
     * 结果码
     */

    private Integer resultCode;
    /**
     * 结果提示
     */
    private String resultMsg;
    /**
     * 结果数据
     */
    private String resultData;
}
