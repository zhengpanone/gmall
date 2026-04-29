package com.zp.module.bpm.entity.definition;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.gmall.framework.common.enums.CommonStatusEnum;
import com.zp.gmall.framework.mybatis.core.dataobject.BaseDO;
import lombok.*;

@TableName("bpm_category")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BpmCategoryDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private String id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类编码
     */
    private String code;

    /**
     * 分类描述
     */
    private String description;

    /**
     * 状态 {@link CommonStatusEnum}
     */
    private String status;

    /**
     * 排序
     */
    private Integer sort;
}
