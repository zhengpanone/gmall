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

/**
 *
 * Description: 租户套餐
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-09
 */
@Data
@TableName(value = "sys_tenant_package", autoResultMap = true)
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TenantPackageDO extends BaseDO {
    @Serial
    private static final long serialVersionUID = 1L;

    @TableId
    private String id;
    /**
     * 套餐编码
     */
    private String code;
    /**
     * 套餐名称
     */
    private String name;

    /**
     * 套餐类型
     */
    private String type;

    /**
     * 套餐描述
     */
    private String description;

    /**
     * 套餐ID
     */
    private String menuIds;

    /**
     * 状态
     * {@link CommonStatusEnum}
     */
    private String status;
}
