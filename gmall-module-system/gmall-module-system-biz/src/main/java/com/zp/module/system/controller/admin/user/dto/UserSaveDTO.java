package com.zp.module.system.controller.admin.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Author : zhengpanone
 * Date : 2023/12/20 17:43
 * Version : v1.0.0
 
 */
@Schema(description = "管理后台-用户创建/修改 DTO")
@Data
public class UserSaveDTO {
    @Schema(description = "用户编号", example = "1024")
    private Long id;
}
