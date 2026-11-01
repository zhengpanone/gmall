package com.zp.gmall.module.system.entity.oauth2;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.gmall.framework.common.enums.UserTypeEnum;
import com.zp.gmall.framework.mybatis.core.dataobject.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * Author : zhengpanone
 * Date : 2025/4/7 18:57
 * Version : v1.0.0
 * Description: OAuth2 批准 DO
 * 用户在 sso.vue 界面时，记录接受的 scope 列表
 */
@TableName(value = "sys_oauth2_approve", autoResultMap = true)
@Data
@EqualsAndHashCode(callSuper = true)
public class OAuth2ApproveDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId
    private String id;

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
    private String scope;

    /**
     * 是否接受
     * true 表示接受，false 表示拒绝
     */
    private Boolean approved;

    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

}
