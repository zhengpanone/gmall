package com.zp.gateway.filter.security;

import lombok.Data;

import java.util.List;

/**
 * Author : zhengpanone
 * Date : 2023/11/20 00:20
 * Version : v1.0.0
 * Description: 登录用户信息
 * copy from yudao-spring-boot-starter-security 的 LoginUser 类
 */
@Data
public class LoginUser {
    /**
     * 用户编号
     */
    private Long id;
    /**
     * 用户类型
     */
    private Integer userType;
    /**
     * 租户编号
     */
    private Long tenantId;
    /**
     * 授权范围
     */
    private List<String> scopes;
}