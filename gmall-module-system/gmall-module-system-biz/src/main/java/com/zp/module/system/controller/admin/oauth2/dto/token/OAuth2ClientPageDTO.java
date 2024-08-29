package com.zp.module.system.controller.admin.oauth2.dto.token;

import com.zp.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Author : zhengpanone
 * Date : 2024/8/6 11:01
 * Version : v1.0.0
 * Description: OAuth2 客户端分页 Request VO
 */
@Schema(description = "管理后台 - OAuth2 客户端分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OAuth2ClientPageDTO extends PageParam {
    @Schema(description = "应用名，模糊匹配", example = "土豆")
    private String name;

    @Schema(description = "状态，参见 CommonStatusEnum 枚举", example = "1")
    private Integer status;
}
