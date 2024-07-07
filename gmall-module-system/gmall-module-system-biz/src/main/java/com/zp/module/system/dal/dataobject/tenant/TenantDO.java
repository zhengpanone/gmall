package com.zp.module.system.dal.dataobject.tenant;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.framework.mybatis.core.dataobject.BaseDO;
import com.zp.module.system.dal.dataobject.user.AdminUserDO;
import com.zp.framework.common.enums.CommonStatusEnum;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Author : zhengpanone
 * Date : 2024/7/7 11:36
 * Version : v1.0.0
 * Description: 租户 DO
 */
@TableName(value = "system_tenant", autoResultMap = true)
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TenantDO extends BaseDO {
    /**
     * 套餐编号 - 系统
     */
    public static final String PACKAGE_ID_SYSTEM = "0";

    /**
     * 租户编号，自增
     */
    private String id;
    /**
     * 租户名，唯一
     */
    private String name;
    /**
     * 联系人的用户编号
     * <p>
     * 关联 {@link AdminUserDO#getId()}
     */
    private Long contactUserId;
    /**
     * 联系人
     */
    private String contactName;
    /**
     * 联系手机
     */
    private String contactMobile;
    /**
     * 租户状态
     * <p>
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;
    /**
     * 绑定域名
     */
    private String website;
    /**
     * 租户套餐编号
     * <p>
     * 关联 {@link TenantPackageDO#getId()}
     * 特殊逻辑：系统内置租户，不使用套餐，暂时使用 {@link #PACKAGE_ID_SYSTEM} 标识
     */
    private String packageId;
    /**
     * 过期时间
     */
    private LocalDateTime expireTime;
    /**
     * 账号数量
     */
    private Integer accountCount;
}
