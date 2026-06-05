package com.zp.gmall.module.system.entity.oauth2;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.zp.gmall.framework.common.enums.UserTypeEnum;
import com.zp.gmall.framework.mybatis.core.dataobject.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * Description: OAuth2 授权码 DO
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-15
 */
@TableName(value = "sys_oauth2_code", autoResultMap = true)
@Data
@EqualsAndHashCode(callSuper = true)
public class OAuth2CodeDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "id")
    private String id;

    /**
     * 授权码
     */
    private String code;

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
     * 重定向URI
     */
    private String redirectUri;

    /**
     * 状态
     */
    private String state;
    /**
     * 过期时间
     */
    private LocalDateTime expireTime;
}
