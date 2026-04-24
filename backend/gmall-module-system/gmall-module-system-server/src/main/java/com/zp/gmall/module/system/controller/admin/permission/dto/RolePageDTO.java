package com.zp.gmall.module.system.controller.admin.permission.dto;

import com.zp.gmall.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author : zhengpanone
 * Date : 2026/4/25 00:55
 * Version : v1.0.0
 * Description:
 */
@Schema(name = "角色分页查询DTO", description = "管理后台 - 角色分页查询DTO")
@Data
public class RolePageDTO extends PageParam {

    @Schema(description = "角色名称")
    private String roleName;

    @Schema(description = "角色编码")
    private String roleCode;

    @Schema(description = "角色类型")
    private Integer roleType;
}
