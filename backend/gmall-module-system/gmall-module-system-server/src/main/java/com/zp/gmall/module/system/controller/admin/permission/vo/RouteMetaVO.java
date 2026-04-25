package com.zp.gmall.module.system.controller.admin.permission.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 路由元信息 VO
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Schema(description = "路由元信息")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteMetaVO {

    @Schema(description = "路由标题", example = "用户管理")
    private String title;

    @Schema(description = "菜单图标", example = "user")
    private String icon;

    @Schema(description = "是否固定标签", example = "false")
    private Boolean affix = false;

    @Schema(description = "是否隐藏菜单", example = "false")
    private Boolean hideMenu = false;

    @Schema(description = "是否隐藏子菜单", example = "false")
    private Boolean hideChildrenInMenu = false;

    @Schema(description = "当前激活的菜单")
    private String currentActiveMenu;

    @Schema(description = "是否忽略权限验证", example = "false")
    private Boolean ignoreAuth = false;

    @Schema(description = "是否忽略路由", example = "false")
    private Boolean ignoreRoute = false;

    @Schema(description = "是否缓存", example = "true")
    private Boolean ignoreKeepAlive = false;

    @Schema(description = "是否在面包屑中隐藏", example = "false")
    private Boolean hideBreadcrumb = false;

    @Schema(description = "是否在标签页中隐藏", example = "false")
    private Boolean hideTab = false;

    @Schema(description = "内嵌iframe地址")
    private String frameSrc;

    @Schema(description = "权限标识列表")
    private List<String> permissions;

    @Schema(description = "角色列表")
    private List<String> roles;

    @Schema(description = "菜单类型: 0-目录, 1-菜单, 2-按钮")
    private Integer type = 1;

    @Schema(description = "菜单编码", example = "system:user")
    private String code;

    @Schema(description = "外部链接")
    private String link;

    @Schema(description = "是否显示根菜单", example = "true")
    private Boolean show = true;

    @Schema(description = "承载组件名")
    private String carryParam;

    @Schema(description = "上级菜单")
    private Long parentMenu;

    @Schema(description = "是否在左侧菜单显示", example = "true")
    private Boolean showInLeftMenu = true;
}