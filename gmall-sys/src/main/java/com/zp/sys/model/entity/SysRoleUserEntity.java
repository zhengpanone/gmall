package com.zp.sys.model.entity;

import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色用户关系
 *
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Table("sys_role_user")
public class SysRoleUserEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 用户ID
     */
    private Long userId;

}
