package com.zp.gmall.module.crm.entity.product;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.gmall.framework.mybatis.core.dataobject.BaseDO;
import com.zp.gmall.module.crm.enums.DictTypeConstants;
import com.zp.gmall.module.crm.enums.product.CrmProductStatusEnum;
import lombok.*;

import java.math.BigDecimal;

/**
 * @author : zhengpanone
 * Date : 2026/5/9 21:46
 * Version : v1.0.0
 * Description: CRM 产品
 */
@TableName("crm_product")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrmProductDO extends BaseDO {

    @TableId
    private String id;

    /**
     * 产品名称
     */
    private String name;

    /**
     * 产品编号
     */
    private String no;

    /**
     * 单位
     * {@link DictTypeConstants#CRM_PRODUCT_UNIT}
     */
    private String unit;

    /**
     * 价格 单位:元
     */
    private BigDecimal price;

    /**
     * 状态
     * {@link CrmProductStatusEnum}
     */
    private String status;

    /**
     * 产品分类ID
     * {@link CrmProductCategoryDO#getId}
     */
    private String categoryId;

    private String description;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 产品拥有者用户ID
     * AdminUserDO#id
     */
    private String ownerUserId;

}
