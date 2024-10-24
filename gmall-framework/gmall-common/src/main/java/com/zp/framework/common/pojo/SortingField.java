package com.zp.framework.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Author : zhengpanone
 * Date : 2023/12/20 19:09
 * Version : v1.0.0
 * Description: 排序字段 DTO
 * 类名加了 ing 的原因是，避免和 ES SortField 重名。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SortingField implements Serializable {
    /**
     * 升序
     */
    public static final String ORDER_ASC = "asc";
    /**
     * 降序
     */
    public static final String ORDER_DESC = "desc";
    /**
     * 字段
     */
    private String field;
    /**
     * 顺序
     */
    private String order;
}
