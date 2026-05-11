package com.zp.gmall.module.crm.entity.product;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.gmall.framework.mybatis.core.dataobject.BaseDO;
import lombok.*;

/**
 * @author : zhengpanone
 * Date : 2026/5/9 22:08
 * Version : v1.0.0
 * Description:
 */
@TableName("crm_product_category")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrmProductCategoryDO extends BaseDO {

    /**
     * 父分类编号 - 根分类
     */
    public static final Long PARENT_ID_NULL = 0L;
    /**
     * 限定分类层级
     */
    public static final int CATEGORY_LEVEL = 2;

    /**
     * 分类编号
     */
    @TableId
    private Long id;
    /**
     * 分类名称
     */
    private String name;
    /**
     * 父级编号
     * <p>
     * 关联 {@link CrmProductCategoryDO#getId}
     */
    private Long parentId;
}
