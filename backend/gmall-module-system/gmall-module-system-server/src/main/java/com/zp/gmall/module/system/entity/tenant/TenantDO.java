package com.zp.gmall.module.system.entity.tenant;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.gmall.framework.mybatis.core.dataobject.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;

/**
 *
 * Description:
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
     * 租户名称
     */
    private String name;

    private String code;

    private String status;

    private String remark;
}
