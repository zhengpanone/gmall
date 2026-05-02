package com.zp.gmall.module.system.entity.dict;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
public class DictDataDO extends BaseDO {

    /**
     * 字典数据编号
     */
    @TableId
    private String id;

    private String typeId;

    private String typeCode;

    /**
     * 字典项编码
     */
    private String dataCode;

    /**
     * 字典项名称
     */
    private String dataName;

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
     * 字典排序
     */
    private Integer sort;

}
