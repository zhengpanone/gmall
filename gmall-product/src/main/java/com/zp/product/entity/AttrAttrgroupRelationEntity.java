package com.zp.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 属性&属性分组关联
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-06-25
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("pms_attr_attrgroup_relation")
public class AttrAttrgroupRelationEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 属性id
     */
	private Long attrId;
    /**
     * 属性分组id
     */
	private Long attrGroupId;
    /**
     * 属性组内排序
     */
	private Integer attrSort;
}