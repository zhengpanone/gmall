package com.zp.gmall.module.product.entity.category;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.gmall.framework.common.enums.CommonStatusEnum;
import com.zp.gmall.framework.mybatis.core.dataobject.BaseDO;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author : zhengpanone
 * Date : 2026/4/29 22:54
 * Version : v1.0.0
 * Description: 商品分类 DO
 */
@TableName("product_category")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategoryDO extends BaseDO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 分类ID
     */
    @TableId(value = "id")
    private String id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 父分类ID
     */
    private String parentId;

    /**
     * 分类层级
     */
    private Integer level;
    /**
     * 分类图标
     */
    private String picUrl;

    /**
     * 分类排序
     */
    private String sort;

    /**
     * 分类状态
     * <p>
     * 枚举 {@link CommonStatusEnum 对应的类}
     */
    private String status;
}
