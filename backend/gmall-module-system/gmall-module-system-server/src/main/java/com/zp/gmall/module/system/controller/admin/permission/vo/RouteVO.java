package com.zp.gmall.module.system.controller.admin.permission.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 路由信息 VO
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "路由信息")
public class RouteVO {

    @Schema(description = "路由路径", example = "/system/user")
    private String path;

    @Schema(description = "路由名称（唯一）", requiredMode = Schema.RequiredMode.REQUIRED, example = "User")
    private String name;

    @Schema(description = "路由组件", example = "system/user/index")
    private String component;

    @Schema(description = "重定向地址", example = "/system/user/list")
    private String redirect;

    @Schema(description = "路由元信息")
    private RouteMetaVO meta;

    @Schema(description = "子路由")
    private List<RouteVO> children;

    @Schema(description = "路由别名")
    private String alias;

    @Schema(description = "是否隐藏", example = "false")
    private Boolean hidden = false;

    @Schema(description = "是否总是显示", example = "true")
    private Boolean alwaysShow = true;

    @Schema(description = "排序", example = "0")
    private Integer orderNo = 0;

    @Schema(description = "权限标识")
    private String permission;
}