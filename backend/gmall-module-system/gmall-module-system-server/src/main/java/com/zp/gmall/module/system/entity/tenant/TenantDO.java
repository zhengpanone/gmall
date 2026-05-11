package com.zp.gmall.module.system.entity.tenant;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.gmall.framework.common.enums.CommonStatusEnum;
import com.zp.gmall.framework.mybatis.core.dataobject.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 *
 * Description: 租户
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-08
 */
@Data
@TableName(value = "sys_tenant", autoResultMap = true)
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TenantDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 租户ID
     */
    @TableId
    private String id;
    /**
     * 租户编号
     */
    private String code;
    /**
     * 租户名称
     */
    private String name;
    /**
     * 联系人ID
     */
    private String contactUserId;

    /**
     * 联系人名称
     */
    private String contactName;
    /**
     * 联系电话
     */
    private String contactPhone;

    /**
     * 统一社会信用代码
     */
    private String licenseNumber;

    /**
     * 地址
     */
    private String address;

    /**
     * 域名
     */
    private String domain;

    /**
     * 简介
     */
    private String intro;

    /**
     * 备注
     */
    private String description;

    /**
     * 租户套餐编号
     */
    private String packageId;
    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

    /**
     * 用户数量（-1不限制）
     */
    private Long accountCount;

    /**
     * 租户状态
     * {@link CommonStatusEnum}
     */
    private String status;
}
