package com.zp.gmall.module.system.entity.oauth2;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.zp.gmall.framework.common.enums.UserTypeEnum;
import com.zp.gmall.framework.mybatis.core.dataobject.BaseDO;
import com.zp.gmall.framework.tenant.core.db.TenantBaseDO;
import com.zp.gmall.module.system.entity.tenant.TenantDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * Description: OAuth2 刷新令牌
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-15
 */
@Data
@TableName(value = "system_oauth2_refresh_token", autoResultMap = true)
@EqualsAndHashCode(callSuper = true)
public class OAuth2RefreshTokenDO extends TenantBaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId
    private String id;

    /**
     * 刷新令牌
     */
    private String refreshToken;


    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户类型
     * 枚举 {@link UserTypeEnum}
     */
    private String userType;

    /**
     * 客户端ID
     * 关联 {@link OAuth2ClientDO#getClientId()}
     */
    private String clientId;

    /**
     * 授权范围
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> scopes;

    /**
     * 过期时间
     */
    private LocalDateTime expireTime;
}
