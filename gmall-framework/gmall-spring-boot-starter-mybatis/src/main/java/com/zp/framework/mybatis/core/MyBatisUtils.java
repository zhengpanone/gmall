package com.zp.framework.mybatis.core;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zp.framework.common.pojo.PageParam;
import com.zp.framework.common.pojo.SortingField;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Author : zhengpanone
 * Date : 2023/12/20 19:04
 * Version : v1.0.0
 * Description: Mybatis工具类
 */
public class MyBatisUtils {
    private static final String MYSQL_ESCAPE_CHARACTER = "`";

    public static <T> Page<T> buildPage(PageParam pageParam) {
        return buildPage(pageParam, null);
    }

    public static <T> Page<T> buildPage(PageParam pageParam, Collection<SortingField> sortingFields) {
// 页码+数量
        Page<T> page = new Page<>(pageParam.getPageNo(), pageParam.getPageSize());
        // 排序字段
        if (CollectionUtil.isEmpty(sortingFields)) {
            page.addOrder(sortingFields.stream().map(sortingField -> SortingField.ORDER_ASC.equals(sortingField.getOrder()) ?
                    OrderItem.asc(sortingField.getField()) : OrderItem.desc(sortingField.getField())).collect(Collectors.toList()));
        }
        return page;
    }
}
