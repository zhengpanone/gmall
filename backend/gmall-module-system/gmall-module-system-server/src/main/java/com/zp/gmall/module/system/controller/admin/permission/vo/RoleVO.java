package com.zp.gmall.module.system.controller.admin.permission.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author : zhengpanone
 * Date : 2026/4/25 00:55
 * Version : v1.0.0
 * Description:
 */
@Schema(name = "角色VO", description = "管理后台 - 角色视图对象")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleVO {

    @Schema(description = "角色ID", example = "1")
    private String id;

    @Schema(description = "角色名称", example = "管理员")
    private String roleName;

    @Schema(description = "角色编码", example = "admin")
    private String roleCode;

    @Schema(description = "角色排序", example = "1")
    private Integer sort;

    @Schema(description = "角色状态", example = "0")
    private Integer status;

    @Schema(description = "角色状态名称", example = "正常")
    private String statusName;

    @Schema(description = "角色类型", example = "1")
    private Integer roleType;

    @Schema(description = "角色类型名称", example = "系统内置")
    private String roleTypeName;

    @Schema(description = "备注", example = "系统管理员角色")
    private String remark;

    @Schema(description = "创建时间", example = "2026-04-25 10:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Schema(description = "更新时间", example = "2026-04-25 10:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
