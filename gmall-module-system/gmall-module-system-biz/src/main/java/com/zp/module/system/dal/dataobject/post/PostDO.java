package com.zp.module.system.dal.dataobject.post;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.framework.common.enums.CommonStatusEnum;
import com.zp.framework.tenant.core.db.TenantBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Author : zhengpanone
 * Date : 2024/8/29 19:57
 * Version : v1.0.0
 * Description: 岗位表
 */
@TableName("sys_post")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class PostDO extends TenantBaseDO {
    /**
     * 岗位序号
     */
    @TableId
    private String id;
    /**
     * 岗位名称
     */
    private String name;
    /**
     * 岗位编码
     */
    private String code;
    /**
     * 岗位排序
     */
    private Integer sort;
    /**
     * 状态
     * <p>
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;
}
