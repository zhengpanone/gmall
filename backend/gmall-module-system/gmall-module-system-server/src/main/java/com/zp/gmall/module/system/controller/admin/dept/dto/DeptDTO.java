package com.zp.gmall.module.system.controller.admin.dept.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.zp.gmall.framework.common.domain.ViewGroup;
import com.zp.gmall.framework.validation.group.CreateGroup;
import com.zp.gmall.framework.validation.group.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 *
 * Project: backend
 * <p>
 * Module: com.zp.gmall.module.system.controller.admin.dept.dto
 * <p>
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-08
 */
@Schema(name = "部门DTO", description = "管理后台 - 部门DTO")
@Data
public class DeptDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "部门ID", example = "1")
    @NotNull(message = "部门ID不能为空", groups = UpdateGroup.class)
    @JsonView(ViewGroup.UpdateView.class)
    private String id;

    @Schema(description = "部门名称", example = "技术部")
    @NotBlank(message = "部门名称不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Size(max = 50, message = "部门名称长度不能超过50个字符", groups = {CreateGroup.class, UpdateGroup.class})
    private String deptName;

    @Schema(description = "部门编码", example = "dept_001")
    @NotBlank(message = "部门编码不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Size(max = 50, message = "部门编码长度不能超过50个字符", groups = {CreateGroup.class, UpdateGroup.class})
    private String deptCode;

    @Schema(description = "父部门ID", example = "0")
    @NotBlank(message = "父部门ID不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    private String parentId;

    @Schema(description = "排序", example = "1")
    @NotNull(message = "排序不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    private Integer sort;

    @Schema(description = "状态：0-禁用 1-启用", example = "1")
    @NotNull(message = "状态不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    private String status;
}
