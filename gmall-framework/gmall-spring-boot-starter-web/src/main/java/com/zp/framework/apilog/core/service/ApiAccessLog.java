package com.zp.framework.apilog.core.service;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Author : zhengpanone
 * Date : 2023/11/14 22:21
 * Version : v1.0.0
 * Description: API访问日志
 */
@Data
public class ApiAccessLog {
    /**
     * 链路追踪编号
     */
    private String traceId;
    /**
     * 用户编号
     */
    private Long userId;
    /**
     * 用户类型
     */
    private Integer userType;
    /**
     * 应用名
     */
    @NotNull(message = "应用名不能为空")
    private Integer applicationName;
    /**
     * 请求方法名
     */
    @NotNull(message = "http请求方法不能为空")
    private String requestMethod;
    /**
     * 请求参数
     */
    @NotNull(message = "请求参数不能为空")
    private String requestParams;
    /**
     * 用户IP
     */
    @NotNull(message = "请求参数不能为空")
    private String userIp;
    /**
     * 浏览器UA
     */
    @NotNull(message = "User-Agent不能为空")
    private String userAgent;
    /**
     * 开始请求时间
     */
    @NotNull(message = "开始请求时间不能为空")
    private LocalDateTime beginTime;
    /**
     * 结束请求时间
     */
    @NotNull(message = "结束请求时间不能为空")
    private LocalDateTime endTime;
    /**
     * 执行时长，单位：毫秒
     */
    @NotNull(message = "执行时长不能为空")
    private Integer duration;
    /**
     * 结果码
     */
    @NotNull(message = "错误码不能为空")
    private Integer resultCode;
    /**
     * 结果提示
     */
    private String resultMsg;
}
