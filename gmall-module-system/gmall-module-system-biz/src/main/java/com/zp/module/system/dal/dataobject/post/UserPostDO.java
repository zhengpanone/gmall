package com.zp.module.system.dal.dataobject.post;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.framework.mybatis.core.dataobject.BaseDO;
import com.zp.module.system.dal.dataobject.user.AdminUserDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Author : zhengpanone
 * Date : 2024/8/29 20:38
 * Version : v1.0.0
 * Description: 用户和岗位关联
 */

@TableName("sys_user_post")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class UserPostDO extends BaseDO {
    /**
     * 自增主键
     */
    @TableId
    private String id;
    /**
     * 用户 ID
     * <p>
     * 关联 {@link AdminUserDO#getId()}
     */
    private String userId;
    /**
     * 角色 ID
     * <p>
     * 关联 {@link PostDO#getId()}
     */
    private String postId;

}
