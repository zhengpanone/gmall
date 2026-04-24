package com.zp.gmall.module.system.entity.dict;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.gmall.framework.common.enums.CommonStatusEnum;
import com.zp.gmall.framework.mybatis.core.dataobject.BaseDO;
import lombok.*;

/**
 * 字典类型表
 *
 * @author ruoyi
 */
@TableName("sys_dict")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DictDO extends BaseDO {

    /**
     * 字典主键
     */
    @TableId
    private String id;

    /**
     * 字典code
     */
    private String dictCode;

    /**
     * 字典名称
     */
    private String dictName;
    /**
     * 字典类型
     */
    private String dictType;
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

    /**
     * 排序
     */
    private Integer sort;

}
