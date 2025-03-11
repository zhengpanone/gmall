package com.zp.gmall.gateway.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * Author : zhengpanone
 * Date : 2025/4/9 11:27
 * Version : v1.0.0
 * Description: 路由数据库实体
 */
@Data
@TableName(value = "gateway_route", autoResultMap = true)
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GatewayRoute {
    // 路由ID
    private String id;

    private String routeId;

    // 目标服务URI
    private String uri;

    // JSON数组
    // 断言规则（JSON格式）
    @TableField(typeHandler = JacksonTypeHandler.class)
    private String predicates;

    // JSON数组
    // 过滤器链（JSON格式）
    @TableField(typeHandler = JacksonTypeHandler.class)
    private String filters;
    // 路由优先级
    private Integer orders;

    private Boolean enabled;

    // 创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    // 更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

}
