package com.zp.gmall.module.system.controller.admin.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * Author : zhengpanone
 * Date : 2025/3/27 20:28
 * Version : v1.0.0
 * Description:
 */
@Schema(description = "管理后台 - 用户 Response VO")
@Data
@Builder
public class AdminUserVO {

    @Schema(description = "用户编号", example = "1")
    private String id;

    @Schema(description = "用户名称", example = "1")
    private String username;

    /**
     * 用户昵称
     */
    @Schema(description = "用户昵称", example = "zhengpanone")
    private String nickname;

    /**
     * 备注
     */
    @Schema(description = "备注", example = "我是一个用户")
    private String remark;

    @Schema(description = "部门编号", example = "1")
    @Builder.Default
    private Set<String> postIds = new HashSet<>();
}
