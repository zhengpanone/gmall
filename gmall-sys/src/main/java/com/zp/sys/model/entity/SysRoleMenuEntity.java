
package com.zp.sys.model.entity;

import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色菜单关系
 *
 * @author 
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Table("sys_role_menu")
public class SysRoleMenuEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 菜单ID
     */
    private Long menuId;

}
