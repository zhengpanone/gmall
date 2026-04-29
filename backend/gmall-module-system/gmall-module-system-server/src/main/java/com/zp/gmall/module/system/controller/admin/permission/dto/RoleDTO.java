package com.zp.gmall.module.system.controller.admin.permission.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.zp.gmall.framework.common.domain.ViewGroup;
import com.zp.gmall.framework.common.validation.ValidateGroup.Create;
import com.zp.gmall.framework.common.validation.ValidateGroup.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author : zhengpanone
 * Date : 2026/4/24 23:41
 * Version : v1.0.0
 * Description:
 */
@Schema(name = "角色保存DTO", description = "管理后台 - 角色保存DTO")
@Data
public class RoleDTO {


    @JsonView(ViewGroup.UpdateView.class)
    @Schema(description = "角色编号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, title = "角色编号", type = "string", example = "1")
    @NotNull(message = "角色ID不能为空", groups = Update.class)
    private String id;

    @JsonView({ViewGroup.CreateView.class, ViewGroup.UpdateView.class})
    @Schema(description = "角色名称", requiredMode = Schema.RequiredMode.REQUIRED, title = "角色名称", type = "string", example = "管理员")
    @NotBlank(message = "角色名称不能为空", groups = {Create.class, Update.class})
    @Size(max = 32, message = "角色名称长度不能超过32个字符", groups = {Create.class, Update.class})
    private String roleName;

    @JsonView({ViewGroup.CreateView.class, ViewGroup.UpdateView.class})
    @Schema(description = "角色编码", requiredMode = Schema.RequiredMode.REQUIRED, title = "角色编码", type = "string", example = "admin")
    @NotBlank(message = "角色编码不能为空", groups = {Create.class, Update.class})
    @Size(max = 32, message = "角色编码长度不能超过32个字符", groups = {Create.class, Update.class})
    private String roleCode;

    @JsonView({ViewGroup.CreateView.class, ViewGroup.UpdateView.class})
    @Schema(description = "角色描述", title = "角色描述", requiredMode = Schema.RequiredMode.NOT_REQUIRED, type = "string", example = "管理员")
    @Size(max = 255, message = "角色描述长度不能超过255个字符", groups = {Create.class, Update.class})
    private String remark;
    
    @JsonView({ViewGroup.CreateView.class, ViewGroup.UpdateView.class})
    @Schema(description = "角色排序", title = "角色排序", requiredMode = Schema.RequiredMode.NOT_REQUIRED, type = "integer", example = "1")
    private Integer sort;

    @JsonView({ViewGroup.CreateView.class, ViewGroup.UpdateView.class})
    @Schema(description = "角色类型", title = "角色类型", requiredMode = Schema.RequiredMode.REQUIRED, type = "string", example = "1")
    @NotBlank(message = "角色类型不能为空", groups = {Create.class, Update.class})
    @Size(max = 32, message = "角色类型长度不能超过32个字符", groups = {Create.class, Update.class})
    private String roleType;
}
