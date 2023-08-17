package com.zp.sys.model.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 角色
 *
 * @author 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Table("sys_role")
public class SysRoleEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     */
    private String name;
    /**
     * 备注
     */
    private String remark;
    /**
     * 部门ID
     * TODO 新增时插入
     */

    private Long deptId;
    /**
     * 更新者
     */
    private Long updater;
    /**
     * 更新时间
     */
    @Column(onUpdateValue = "now()")
    private Date updateDate;
}
