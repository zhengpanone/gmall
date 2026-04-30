package com.zp.gmall.framework.common.util.object;

import cn.hutool.core.bean.BeanUtil;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.framework.common.util.collection.CollectionUtils;

import java.io.Serializable;
import java.util.List;
import java.util.function.Consumer;

/**
 * Author : zhengpanone
 * Date : 2024/7/7 11:48
 * Version : v1.0.0
 * Description: Bean 工具类
 * 1. 默认使用 {@link cn.hutool.core.bean.BeanUtil} 作为实现类，
 * 2. 针对复杂的对象转换，可以搜参考 AuthConvert 实现，通过 mapstruct + default 配合实现
 */
public class BeanUtils {

    /**
     * 将源对象转换为目标类型的对象
     *
     * @param source      源对象
     * @param targetClass 目标类型
     * @param <S>         源类型
     * @param <T>         目标类型
     * @return 转换后的对象
     */
    public static <S, T> T toBean(S source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }
        return BeanUtil.toBean(source, targetClass);
    }

    /**
     * 将源对象转换为目标类型的对象，并执行后续操作
     *
     * @param source      源对象
     * @param targetClass 目标类型
     * @param peek        转换后的操作
     * @param <S>         源类型
     * @param <T>         目标类型
     * @return 转换后的对象
     */
    public static <S, T> T toBean(S source, Class<T> targetClass, Consumer<T> peek) {
        T target = toBean(source, targetClass);
        if (target != null) {
            peek.accept(target);
        }
        return target;
    }

    /**
     * 将源对象列表转换为目标类型的对象列表
     *
     * @param source     源对象列表
     * @param targetType 目标类型
     * @param <S>        源类型
     * @param <T>        目标类型
     * @return 转换后的对象列表
     */
    public static <S, T> List<T> toBean(List<S> source, Class<T> targetType) {
        if (source == null) {
            return null;
        }
        return CollectionUtils.convertList(source, s -> toBean(s, targetType));
    }

    /**
     * 将源对象列表转换为目标类型的对象列表，并对每个元素执行操作
     *
     * @param source     源对象列表
     * @param targetType 目标类型
     * @param peek       对每个转换后元素的操作
     * @param <S>        源类型
     * @param <T>        目标类型
     * @return 转换后的对象列表
     */
    public static <S, T> List<T> toBean(List<S> source, Class<T> targetType, Consumer<T> peek) {
        List<T> list = toBean(source, targetType);
        if (list != null) {
            list.forEach(peek);
        }
        return list;
    }

    /**
     * 将 PageResult 中的列表数据转换为目标类型
     *
     * @param source     源 PageResult
     * @param targetType 目标类型
     * @param <S>        源数据类型（必须实现 Serializable）
     * @param <T>        目标数据类型（必须实现 Serializable）
     * @return 转换后的 PageResult
     */
    public static <S extends Serializable, T extends Serializable> PageResult<T> toBean(PageResult<S> source, Class<T> targetType) {
        return toBean(source, targetType, null);
    }

    /**
     * 将 PageResult 中的列表数据转换为目标类型，并对每个元素执行操作
     *
     * @param source     源 PageResult
     * @param targetType 目标类型
     * @param peek       对每个转换后元素的操作
     * @param <S>        源数据类型（必须实现 Serializable）
     * @param <T>        目标数据类型（必须实现 Serializable）
     * @return 转换后的 PageResult
     */
    public static <S extends Serializable, T extends Serializable> PageResult<T> toBean(PageResult<S> source, Class<T> targetType, Consumer<T> peek) {
        if (source == null) {
            return null;
        }
        List<T> list = toBean(source.getList(), targetType);
        if (peek != null) {
            list.forEach(peek);
        }
        return new PageResult<>(list, source.getTotal(), source.getPageNum(), source.getPageSize());
    }

    /**
     * 将 PageResult 中的列表数据转换为目标类型（简化版本）
     *
     * @param source     源 PageResult
     * @param targetType 目标类型
     * @param <S>        源数据类型
     * @param <T>        目标数据类型
     * @return 转换后的 PageResult
     */
    public static <S extends Serializable, T extends Serializable> PageResult<T> convertPage(PageResult<S> source, Class<T> targetType) {
        if (source == null) {
            return null;
        }
        List<T> list = toBean(source.getList(), targetType);
        PageResult<T> result = new PageResult<>(list != null ? list : java.util.Collections.emptyList(), source.getTotal(), source.getPageNum(), source.getPageSize());
        result.setCode(source.getCode());
        result.setMsg(source.getMsg());
        return result;
    }

    /**
     * 将对象属性复制到目标对象
     *
     * @param source 源对象
     * @param target 目标对象
     * @param <S>    源类型
     * @param <T>    目标类型
     */
    public static <S, T> void copyProperties(S source, T target) {
        if (source == null || target == null) {
            return;
        }
        BeanUtil.copyProperties(source, target);
    }

    /**
     * 将对象属性复制到目标对象，忽略空值
     *
     * @param source 源对象
     * @param target 目标对象
     * @param <S>    源类型
     * @param <T>    目标类型
     */
    public static <S, T> void copyPropertiesIgnoreNull(S source, T target) {
        if (source == null || target == null) {
            return;
        }
        BeanUtil.copyProperties(source, target, true);
    }
}
