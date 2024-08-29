package com.zp.module.system.dal.dataobject.logger;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.framework.common.enums.UserTypeEnum;
import com.zp.framework.mybatis.core.dataobject.BaseDO;
import com.zp.module.system.enums.logger.LoginLogTypeEnum;
import com.zp.module.system.enums.logger.LoginResultEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Author : zhengpanone
 * Date : 2024/8/5 11:16
 * Version : v1.0.0
 * Description: 登录日志表,包括登录和登出两种行为
 */

@TableName("sys_login_log")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
public class LoginLogDO extends BaseDO {
    /**
     * 日志主键
     */
    private Long id;
    /**
     * 日志类型
     * 枚举 {@link LoginLogTypeEnum}
     */
    private Integer logType;
    /**
     * 链路追踪编号
     */
    private String traceId;
    /**
     * 用户编号
     */
    private Long userId;
    /**
     * 用户类型
     * 枚举 {@link UserTypeEnum}
     */
    private Integer userType;
    /**
     * 用户账号
     * 冗余，因为账号可以变更
     */
    private String username;
    /**
     * 登录结果
     * 枚举 {@link LoginResultEnum}
     */
    private Integer result;
    /**
     * 用户 IP
     */
    private String userIp;
    /**
     * 浏览器 UA
     */
    private String userAgent;
}
