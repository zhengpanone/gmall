package com.zp.gmall.module.system.entity.permission;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.gmall.framework.mybatis.core.dataobject.BaseDO;
import lombok.*;
import lombok.experimental.Accessors;

@Data
@TableName("sys_menu")
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MenuDO extends BaseDO {

    private String id;
    /**
     * 父菜单ID
     */
    private String parentId = "0";
    /**
     * 祖级列表
     */
    private String ancestorIds = "";
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单标题
     */
    private String title;

    /**
     * 菜单标识
     */
    private String code;

    /**
     * 路由地址
     */
    private String path;
    /**
     * 组件路径
     */
    private String component;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 显示顺序
     */
    private Integer sortOrder = 0;
    /**
     * 菜单类型（1目录 2菜单 3按钮 4外链）
     */
    private Integer type;

    /**
     * 是否为外链（0否 1是）
     */
    private Boolean isFrame = false;

    /**
     * 是否缓存（0否 1是）
     */
    private Boolean isCache = false;

    /**
     * 是否显示面包屑（0否 1是）
     */
    private Boolean isBreadcrumb = true;

    /**
     * 是否重定向（0否 1是）
     */
    private Boolean isRedirect = false;

    /**
     * 重定向路径
     */
    private String redirectPath;

    /**
     * 权限标识
     */

    private String permission;

    /**
     * 菜单状态（0停用 1正常）
     */
    private Boolean status = true;

    /**
     * 备注
     */
    private String remark;

    /**
     * 显示状态（0隐藏 1显示）
     */
    private Boolean visible;

    /**
     * 是否缓存
     */
    private Boolean keepAlive;
    /**
     * 是否固定标签（0否 1是）
     */
    private Boolean affix;
    /**
     * 外链地址
     */
    private String frameSrc;

}