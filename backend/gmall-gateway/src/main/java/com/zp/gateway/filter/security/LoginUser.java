package com.zp.gateway.filter.security;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/**
 * Author : zhengpanone
 * Date : 2023/11/20 00:20
 * Version : v1.0.0
 * Description: 登录用户信息
 * copy from yudao-spring-boot-starter-security 的 LoginUser 类
 */
@Data
@Accessors(chain = true)
public class LoginUser {
    /**
     * 用户编号
     */
    private String id;
    /**
     * 用户类型
     */
    private Integer userType;
    /**
     * 额外的用户信息
     */
    private Map<String, String> info;
    /**
     * 租户编号
     */
    private String tenantId;
    /**
     * 授权范围
     */
    private List<String> scopes;
}
