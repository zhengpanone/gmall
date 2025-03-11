package com.zp.gmall.module.system.entity.dept;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Author : zhengpanone
 * Date : 2025/4/10 16:27
 * Version : v1.0.0
 * Description: 部门闭包表
 */
@Data
@TableName("system_dept_closure")
public class DeptClosureDO {
    /**
     * 祖先部门ID
     */
    private Long ancestor;

    /**
     * 后代部门ID
     */
    private Long descendant;

    /**
     * 深度：祖先到后代的距离
     */
    private Integer depth;
}
