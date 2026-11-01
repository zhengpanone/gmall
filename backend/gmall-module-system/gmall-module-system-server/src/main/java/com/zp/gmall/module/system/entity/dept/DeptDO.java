package com.zp.gmall.module.system.entity.dept;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.gmall.framework.tenant.core.db.TenantBaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;

/**
 *
 * Project: backend
 * <p>
 * Module: com.zp.gmall.module.system.entity.dept
 * <p>
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-08
 */
@Data
@TableName(value = "sys_dept", autoResultMap = true)
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeptDO extends TenantBaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId
    private String id;

    private String code;

    private String name;

    private String parentId;

    private String parentName;

    private String fullName;

    private String sort;

    private String status;
}
