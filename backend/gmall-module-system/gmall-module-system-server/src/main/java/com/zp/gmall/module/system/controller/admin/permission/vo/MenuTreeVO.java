package com.zp.gmall.module.system.controller.admin.permission.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author : zhengpanone
 * Date : 2026/4/26 00:42
 * Version : v1.0.0
 * Description: 菜单树VO
 */
@Data
@Schema(title = "菜单树VO")
public class MenuTreeVO {

    @Schema(title = "菜单ID")
    private String id;

    @Schema(title = "父菜单ID")
    private String parentId;

    @Schema(title = "菜单名称")
    private String name;

    @Schema(title = "菜单标识")
    private String code;

    @Schema(title = "菜单类型")
    private Integer type;

    @Schema(title = "排序")
    private Integer sort;

    @Schema(title = "子菜单")
    private List<MenuTreeVO> children;
}
