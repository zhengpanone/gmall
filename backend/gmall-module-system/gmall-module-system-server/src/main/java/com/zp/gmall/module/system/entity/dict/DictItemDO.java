package com.zp.gmall.module.system.entity.dict;

import com.baomidou.mybatisplus.annotation.*;
import com.zp.gmall.framework.common.enums.CommonStatusEnum;
import com.zp.gmall.framework.mybatis.core.dataobject.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : zhengpanone
 * Date : 2026/4/15 20:51
 * Version : v1.0.0
 * Description:
 */
@TableName("sys_dict_data")
@Data
@EqualsAndHashCode(callSuper = true)
public class DictItemDO extends BaseDO {

    /**
     * 字典数据编号
     */
    @TableId
    private String id;

    private String dictId;

    /**
     * 字典标签
     */
    private String label;

    /**
     * 字典项编码
     */
    private String itemCode;

    /**
     * 字典项名称
     */
    private String itemValue;

    /**
     * 状态
     * <p>
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;
    /**
     * 颜色类型
     * <p>
     * 对应到 element-ui 为 default、primary、success、info、warning、danger
     */
    private String colorType;

    /**
     * css 样式
     */
    @TableField(updateStrategy = FieldStrategy.ALWAYS)
    private String cssClass;

    /**
     * 备注
     */
    private String remark;

    /**
     * 字典排序
     */
    private Integer sort;

}
