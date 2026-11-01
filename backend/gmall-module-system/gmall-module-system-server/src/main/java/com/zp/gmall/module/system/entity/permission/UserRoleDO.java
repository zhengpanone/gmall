package com.zp.gmall.module.system.entity.permission;

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
 * @author zhengpan
 * Date: 2025-05-13 15:11:00
 * Version: v1.0.0
 * Description: 用户-角色关联表
 */
@Data
@TableName(value = "sys_user_role", autoResultMap = true)
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId
    private String id;

    private String userId;

    private String roleId;

}
