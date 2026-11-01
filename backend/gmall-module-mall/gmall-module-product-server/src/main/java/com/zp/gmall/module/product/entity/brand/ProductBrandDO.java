package com.zp.gmall.module.product.entity.brand;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.gmall.framework.common.enums.CommonStatusEnum;
import com.zp.gmall.framework.mybatis.core.dataobject.BaseDO;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author : zhengpanone
 * Date : 2026/4/29 22:52
 * Version : v1.0.0
 * Description: 商品品牌 DO
 */
@TableName("product_brand")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductBrandDO extends BaseDO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 品牌ID
     */
    @TableId
    private String id;

    /**
     * 品牌名称
     */
    private String name;

    /**
     * 品牌logo
     */
    private String logoUrl;

    /**
     * 品牌大图
     */
    private String bigPicUrl;

    /**
     * 品牌描述
     */
    private String description;

    /**
     * 品牌排序
     */
    private String sort;
    /**
     * 品牌状态
     * <p>
     * 枚举 {@link CommonStatusEnum 对应的类}
     */
    private String status;


}
