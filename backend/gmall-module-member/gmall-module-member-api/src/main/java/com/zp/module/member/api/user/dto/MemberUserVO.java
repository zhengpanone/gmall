package com.zp.module.member.api.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Author : zhengpanone
 * Date : 2024/7/31 17:00
 * Version : v1.0.0
 * Description:
 */
@Schema(description = "RPC 服务 - 用户信息 Response VO")
@Data
public class MemberUserVO {
    @Schema(description = "用户编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private String id;

    @Schema(description = "昵称", example = "小王同学")
    private String nickname;

    @Schema(description = "帐号状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Integer status; // 参见 CommonStatusEnum 枚举

    @Schema(description = "用户头像", example = "https://www.iocoder.cn/xxx.jpg")
    private String avatar;

    @Schema(description = "手机号", example = "15601691300")
    private String mobile;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

    // ========== 其它信息 ==========

    @Schema(description = "会员级别编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long levelId;

    @Schema(description = "积分", requiredMode = Schema.RequiredMode.REQUIRED, example = "886")
    private Integer point;

}
