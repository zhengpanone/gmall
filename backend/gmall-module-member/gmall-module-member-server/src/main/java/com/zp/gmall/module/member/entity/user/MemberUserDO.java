package com.zp.gmall.module.member.entity.user;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.gmall.framework.tenant.core.db.TenantBaseDO;
import lombok.*;

/**
 * @author : zhengpanone
 * Date : 2026/4/29 22:01
 * Version : v1.0.0
 * Description:
 */
@TableName(value = "member_user", autoResultMap = true)
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberUserDO extends TenantBaseDO {
    /**
     * 用户ID
     */
    @TableId
    private String id;
    /**
     * 手机
     */
    private String mobile;
}
