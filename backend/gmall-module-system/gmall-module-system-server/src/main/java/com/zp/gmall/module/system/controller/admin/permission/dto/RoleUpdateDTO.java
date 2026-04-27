package com.zp.gmall.module.system.controller.admin.permission.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author : zhengpanone
 * Date : 2026/4/25 01:02
 * Version : v1.0.0
 * Description:
 */
@Schema(name = "角色更新DTO", description = "管理后台 - 角色更新DTO")
@Data
public class RoleUpdateDTO {

    @Schema(description = "角色ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotBlank(message = "角色ID不能为空")
    private String id;

    @Schema(description = "角色名称", requiredMode = Schema.RequiredMode.REQUIRED, title = "角色名称", type = "string", example = "管理员")
    @NotBlank(message = "角色名称不能为空")
    @Size(max = 32, message = "角色名称长度不能超过32个字符")
    private String roleName;

    @Schema(description = "角色编码", requiredMode = Schema.RequiredMode.REQUIRED, title = "角色编码", type = "string", example = "admin")
    @NotBlank(message = "角色编码不能为空")
    @Size(max = 32, message = "角色编码长度不能超过32个字符")
    private String roleCode;

    @Schema(description = "角色排序", title = "角色排序", type = "integer", example = "1")
    private Integer sort;

    @Schema(description = "角色状态", title = "角色状态", type = "integer", example = "0")
    private Integer status;

    @Schema(description = "角色描述", title = "角色描述", requiredMode = Schema.RequiredMode.NOT_REQUIRED, type = "string", example = "管理员")
    @Size(max = 255, message = "角色描述长度不能超过255个字符")
    private String desc;

    @Schema(description = "角色类型", title = "角色类型", requiredMode = Schema.RequiredMode.REQUIRED, type = "string", example = "1")
    @NotBlank(message = "角色类型不能为空")
    @Size(max = 32, message = "角色类型长度不能超过32个字符")
    private String roleType;
}
