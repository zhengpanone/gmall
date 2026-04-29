package com.zp.gmall.module.product.entity.brand;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.gmall.framework.mybatis.core.dataobject.BaseDO;
import lombok.*;

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
public class ProductBrandDO extends BaseDO {
    @TableId
    private String id;
}
