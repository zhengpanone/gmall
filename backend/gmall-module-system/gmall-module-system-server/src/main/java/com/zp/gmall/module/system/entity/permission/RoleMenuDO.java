package com.zp.gmall.module.system.entity.permission;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.gmall.framework.mybatis.core.dataobject.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author : zhengpanone
 * Date : 2026/4/25 21:47
 * Version : v1.0.0
 * Description:
 */
@Data
@TableName(value = "sys_role_menu", autoResultMap = true)
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleMenuDO extends BaseDO {
    private String roleId;
    private String menuId;
}
