package com.zp.gmall.module.system.controller.admin.permission.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

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
@Schema(title = "菜单保存DTO", description = "菜单保存传输对象")
public class MenuDTO {

    @Schema(title = "菜单ID", description = "菜单ID", example = "1", requiredMode = RequiredMode.REQUIRED)
    //@NotNull(message = "菜单ID不能为空")
    private String id;

    @NotBlank(message = "父菜单ID不能为空")
    @Size(max = 36, message = "父菜单ID长度不能超过36个字符")
    @Schema(title = "父菜单ID", description = "父菜单ID", example = "0")
    private String parentId;
    /**
     * 菜单名称
     */
    @NotBlank(message = "菜单名称不能为空")
    @Size(max = 50, message = "菜单名称长度不能超过50个字符")
    @Schema(title = "菜单名称", description = "菜单名称", example = "系统管理")
    private String menuName;

    @NotBlank(message = "菜单编码不能为空")
    @Size(max = 50, message = "菜单编码长度不能超过50个字符")
    @Schema(title = "菜单编码", description = "菜单编码", example = "System", requiredMode = RequiredMode.REQUIRED)
    @Pattern(regexp = "^[A-Z][a-zA-Z0-9]*$", message = "菜单标识必须以大写字母开头，只能包含字母和数字")
    private String menuCode;


    @Schema(description = "路由地址", example = "/system")
    @Size(max = 255, message = "路由地址长度不能超过255个字符")
    private String path;

    @Schema(description = "菜单类型（1目录 2菜单 3按钮 4外链）", requiredMode = RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "菜单类型不能为空")
    @Range(min = 1, max = 4, message = "菜单类型必须在1-4之间")
    private Integer menuType;

    @Schema(description = "组件路径", example = "system/user/index")
    @Size(max = 500, message = "组件路径长度不能超过500个字符")
    private String component;

    @Schema(description = "菜单图标", example = "system")
    @Size(max = 100, message = "菜单图标长度不能超过100个字符")
    private String icon;

    private Integer sort;

    @Schema(description = "权限标识", example = "system:user:list")
    @Size(max = 100, message = "权限标识长度不能超过100个字符")
    @Pattern(regexp = "^[a-z]+(:[a-z]+)*$", message = "权限标识格式不正确，应为小写字母和冒号组成，如：system:user:list")
    private String permission;

    @Schema(description = "备注")
    @Size(max = 500, message = "备注长度不能超过500个字符")
    private String remark;

    @Schema(description = "菜单可见性", example = "true")
    private Boolean visible;

    @Schema(description = "菜单状态", example = "true")
    private Boolean status = true;

    @Schema(description = "是否缓存", example = "true")
    private Boolean keepAlive;

    @Schema(description = "是否固定标签", example = "true")
    private Boolean affix;

    @Schema(description = "是否外链", example = "true")
    private Boolean isFrame;

    @Schema(description = "外链地址", example = "https://www.baidu.com")
    private String frameSrc;

    @Schema(description = "是否重定向", example = "true")
    private Boolean isRedirect;

    @Schema(description = "重定向路径", example = "/system/user/list")
    private String redirectPath;

    @Schema(description = "是否显示面包屑", example = "true")
    private Boolean isBreadcrumb;

}
