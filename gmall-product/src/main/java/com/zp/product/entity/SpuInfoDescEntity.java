package com.zp.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * spu信息介绍
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-06-25
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("pms_spu_info_desc")
public class SpuInfoDescEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
	private Long spuId;
    /**
     * 商品介绍
     */
	private String decript;
}