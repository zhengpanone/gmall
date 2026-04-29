package com.zp.gmall.module.product.entity.category;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.gmall.framework.mybatis.core.dataobject.BaseDO;
import lombok.*;

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
public class ProductCategoryDO extends BaseDO {

    @TableId(value = "id")
    private String id;

    private String name;

    private String parentId;


    private String sort;

    private String status;
}
