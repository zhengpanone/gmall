package com.zp.module.system.dal.dataobject.tenant;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.framework.mybatis.core.dataobject.BaseDO;
import com.zp.framework.mybatis.core.type.JsonLongSetTypeHandler;
import com.zp.framework.common.enums.CommonStatusEnum;
import lombok.*;

import java.util.Set;

/**
 * Author : zhengpanone
 * Date : 2024/7/7 11:39
 * Version : v1.0.0
 * Description: 租户套餐 DO
 */
@TableName(value = "sys_tenant_package", autoResultMap = true)
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TenantPackageDO extends BaseDO {
    /**
     * 套餐编号，自增
     */
    private String id;
    /**
     * 套餐名，唯一
     */
    private String name;
    /**
     * 租户套餐状态
     * <p>
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 关联的菜单编号
     */
    @TableField(typeHandler = JsonLongSetTypeHandler.class)
    private Set<Long> menuIds;

}
