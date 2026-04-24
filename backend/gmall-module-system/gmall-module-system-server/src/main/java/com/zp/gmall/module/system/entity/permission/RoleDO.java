package com.zp.gmall.module.system.entity.permission;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.zp.gmall.framework.common.enums.CommonStatusEnum;
import com.zp.gmall.framework.tenant.core.db.TenantBaseDO;
import com.zp.gmall.module.system.enums.permission.DataScopeEnum;
import com.zp.gmall.module.system.enums.permission.RoleTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Set;

/**
 * @author : zhengpanone
 * Date : 2026/4/24 23:42
 * Version : v1.0.0
 * Description:
 */
@Data
@TableName(value = "sys_role", autoResultMap = true)
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDO extends TenantBaseDO {

    /**
     * 角色ID
     */
    @TableId
    private String id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色标识
     * <p>
     * 枚举
     */
    private String code;
    /**
     * 角色排序
     */
    private Integer sort;
    /**
     * 角色状态
     * <p>
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;
    /**
     * 角色类型
     * <p>
     * 枚举 {@link RoleTypeEnum}
     */
    private Integer type;
    /**
     * 备注
     */
    private String remark;

    ///**
    // * 数据范围
    // *
    // * 枚举 {@link DataScopeEnum}
    // */
    //private Integer dataScope;

}
