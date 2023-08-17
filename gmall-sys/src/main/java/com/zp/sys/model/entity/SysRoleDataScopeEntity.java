/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.zp.sys.model.entity;

import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色数据权限
 *
 * @author
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Table("sys_role_data_scope")
public class SysRoleDataScopeEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 部门ID
     */
    private Long deptId;

}