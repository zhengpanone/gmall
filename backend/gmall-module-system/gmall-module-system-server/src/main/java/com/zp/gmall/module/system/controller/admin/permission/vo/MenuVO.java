package com.zp.gmall.module.system.controller.admin.permission.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author : zhengpanone
 * Date : 2026/4/25 22:13
 * Version : v1.0.0
 * Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "菜单VO", description = "菜单视图对象")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuVO {

    @Schema(title = "菜单ID", description = "菜单ID", example = "1")
    private String id;

    @Schema(title = "父菜单ID", description = "父菜单ID", example = "0")
    private String parentId;

    @Schema(title = "菜单名称", description = "菜单名称", example = "系统管理")
    private String name;

    @Schema(title = "菜单标题", description = "菜单标题", example = "系统管理")
    private String title;

    @Schema(title = "路由地址", description = "路由地址", example = "/system")
    private String path;

    @Schema(title = "组件路径", description = "组件路径", example = "system/user/index")
    private String component;

    @Schema(title = "菜单图标", description = "菜单图标", example = "system")
    private String icon;

    @Schema(title = "菜单标识", description = "菜单标识", example = "System")
    private String code;

    @Schema(title = "排序", description = "排序", example = "1")
    private Integer sort;

    @Schema(title = "菜单类型", description = "菜单类型", example = "0")
    private Integer type;

    @Schema(title = "权限标识", description = "权限标识", example = "system:user:list")
    private String permission;

    @Schema(title = "是否可见", description = "是否可见", example = "true")
    private Boolean visible;

    @Schema(title = "状态", description = "状态", example = "true")
    private Integer status;

    @Schema(title = "是否缓存", description = "是否缓存", example = "true")
    private Boolean keepAlive;

    @Schema(title = "是否固定标签", description = "是否固定标签", example = "true")
    private Boolean affix;

    @Schema(title = "外链地址", description = "外链地址", example = "https://www.baidu.com")
    private String frameSrc;

    @Schema(title = "子菜单", description = "子菜单", example = "[]")
    private List<MenuVO> children;
}
