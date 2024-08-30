package com.zp.module.system.dal.dataobject.dept;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.framework.common.enums.CommonStatusEnum;
import com.zp.framework.tenant.core.db.TenantBaseDO;
import com.zp.module.system.dal.dataobject.user.AdminUserDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Author : zhengpanone
 * Date : 2024/8/29 12:38
 * Version : v1.0.0
 * Description: 部门表
 */
@TableName("sys_dept")
@Data
@EqualsAndHashCode(callSuper = true)
public class DeptDO extends TenantBaseDO {
    public static final String PARENT_ID_ROOT = "0";

    /**
     * 部门ID
     */
    @TableId
    private String id;
    /**
     * 部门名称
     */
    private String name;
    /**
     * 父部门ID
     *
     * 关联 {@link #id}
     */
    private String parentId;
    /**
     * 显示顺序
     */
    private Integer sort;
    /**
     * 负责人
     *
     * 关联 {@link AdminUserDO#getId()}
     */
    private String leaderUserId;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 部门状态
     *
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;
}
