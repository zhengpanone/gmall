package com.zp.gmall.framework.common.biz.oauth2.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Schema(description = "RPC 服务 - OAuth2 访问令牌的校验 Response DTO")
@Data
public class OAuth2AccessTokenCheckVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "用户编号", example = "10")
    private String userId;

    @Schema(description = "用户类型，参见 UserTypeEnum 枚举", example = "1")
    private String userType;

    @Schema(description = "用户信息", example = "{\"nickname\": \"芋道\"}")
    private Map<String, String> userInfo;

    @Schema(description = "租户编号", example = "1024")
    private String tenantId;

    @Schema(description = "授权范围的数组", example = "user_info")
    private List<String> scopes;

}
