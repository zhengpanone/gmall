package com.zp.sys.mapper;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.mapper.Mapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.ExceptionUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.*;

/**
 * https://piemoe.com/posts/32
 *
 * @param <T>
 */
@SuppressWarnings({"DuplicatedCode", "unused"})
public interface OptionalBaseMapper<T> extends BaseMapper<T> {


    // ======================================= 批量插入 ========================================

    /**
     * 批量插入 仅适用于mysql
     *
     * @param entityList 实体列表
     * @return 影响行数
     */
    Integer insertBatchSomeColumn(Collection<T> entityList);

    /**
     * 逐条插入，适合少量数据插入，或者对性能要求不高的场景
     * <p>
     * 如果大量，请使用 {@link com.baomidou.mybatisplus.extension.service.impl.ServiceImpl#saveBatch(Collection)} 方法
     * 使用示例，可见 RoleMenuBatchInsertMapper、UserRoleBatchInsertMapper 类
     *
     * @param entities 实体们
     */
    default void insertBatch(Collection<T> entities) {
        entities.forEach(this::insert);
    }

    // ======================================= 查询数量 ========================================


    /**
     * 根据 Wrapper 条件，查询总记录数
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    default Optional<Long> getCount(Wrapper<T> queryWrapper) {
        Long count = selectCount(queryWrapper);
        return ObjectUtil.isNull(count) ? Optional.empty() : Optional.of(count);
    }

    /**
     * 查询指定字段值数量
     *
     * @param field field
     * @param value value
     */
    default Optional<Long> selectCount(String field, Object value) {
        Long count = selectCount(new QueryWrapper<T>().eq(field, value));
        return ObjectUtil.isNull(count) ? Optional.empty() : Optional.of(count);
    }

    /**
     * 查询指定字段值数量
     *
     * @param field field
     * @param value value
     */
    default Optional<Long> selectCount(SFunction<T, ?> field, Object value) {
        Long count = selectCount(new LambdaQueryWrapper<T>().eq(field, value));
        return ObjectUtil.isNull(count) ? Optional.empty() : Optional.of(count);
    }

    // ======================================= 查询一个 ========================================


    /**
     * 根据 ID 查询
     *
     * @param id 主键ID
     */
    default Optional<T> getById(Serializable id) {
        T t = selectById(id);
        return ObjectUtil.isNull(t) ? Optional.empty() : Optional.of(t);
    }


    /**
     * 根据 entity 条件，查询一条记录
     * <p>查询一条记录，例如 qw.last("limit 1") 限制取一条记录, 注意：多条数据会报异常</p>
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    default Optional<T> getOne(Wrapper<T> queryWrapper) {
        T t = selectOne(queryWrapper);
        return ObjectUtil.isNull(t) ? Optional.empty() : Optional.of(t);
    }

    /**
     * 查询一个
     *
     * @param field field
     * @param value value
     */
    default Optional<T> getOne(String field, Object value) {
        return getOne(new QueryWrapper<T>().eq(field, value));
    }

    /**
     * 查询一个
     *
     * @param field field
     * @param value value
     */
    default Optional<T> getOne(SFunction<T, ?> field, Object value) {
        return getOne(new LambdaQueryWrapper<T>().eq(field, value));
    }

    /**
     * 查询一个
     *
     * @param field1 field1
     * @param value1 value1
     * @param field2 field2
     * @param value2 value2
     */
    default Optional<T> getTwo(String field1, Object value1, String field2, Object value2) {
        return getOne(new QueryWrapper<T>().eq(field1, value1).eq(field2, value2));
    }

    /**
     * 查询一个
     *
     * @param field1 field1
     * @param value1 value1
     * @param field2 field2
     * @param value2 value2
     */
    default Optional<T> getTwo(SFunction<T, ?> field1, Object value1, SFunction<T, ?> field2, Object value2) {
        return getOne(new LambdaQueryWrapper<T>().eq(field1, value1).eq(field2, value2));
    }


    // ======================================= 查询多个 ========================================


    /**
     * 查询（根据ID 批量查询）
     *
     * @param idList 主键ID列表(不能为 null 以及 empty)
     */
    default Optional<List<T>> listBatchIds(Collection<? extends Serializable> idList) {
        List<T> list = selectBatchIds(idList);
        return CollUtil.isEmpty(list) ? Optional.empty() : Optional.of(list);
    }

    /**
     * 根据 entity 条件，查询全部记录
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    default Optional<List<T>> list(Wrapper<T> queryWrapper) {
        List<T> list = selectList(queryWrapper);
        return CollUtil.isEmpty(list) ? Optional.empty() : Optional.of(list);
    }

    /**
     * 列表查询
     *
     * @param field field
     * @param value value
     */
    default Optional<List<T>> list(String field, Object value) {
        List<T> list = selectList(new QueryWrapper<T>().eq(field, value));
        return CollUtil.isEmpty(list) ? Optional.empty() : Optional.of(list);
    }

    /**
     * 列表查询
     *
     * @param field field
     * @param value value
     */
    default Optional<List<T>> list(SFunction<T, ?> field, Object value) {
        List<T> list = selectList(new LambdaQueryWrapper<T>().eq(field, value));
        return CollUtil.isEmpty(list) ? Optional.empty() : Optional.of(list);
    }

    /**
     * 列表查询
     *
     * @param field  field
     * @param values values
     */
    default Optional<List<T>> list(String field, Collection<?> values) {
        List<T> list = selectList(new QueryWrapper<T>().in(field, values));
        return CollUtil.isEmpty(list) ? Optional.empty() : Optional.of(list);
    }

    /**
     * 列表查询
     *
     * @param field  field
     * @param values values
     */
    default Optional<List<T>> list(SFunction<T, ?> field, Collection<?> values) {
        List<T> list = selectList(new LambdaQueryWrapper<T>().in(field, values));
        return CollUtil.isEmpty(list) ? Optional.empty() : Optional.of(list);
    }


    /**
     * 查询（根据 columnMap 条件）
     *
     * @param columnMap 表字段 map 对象
     */
    default Optional<List<T>> listByMaps(Map<String, Object> columnMap) {
        List<T> list = selectByMap(columnMap);
        return CollUtil.isEmpty(list) ? Optional.empty() : Optional.of(list);
    }

    /**
     * 根据 Wrapper 条件，查询全部记录
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    default Optional<List<Map<String, Object>>> maps(Wrapper<T> queryWrapper) {
        List<Map<String, Object>> maps = selectMaps(queryWrapper);
        return CollUtil.isEmpty(maps) ? Optional.empty() : Optional.of(maps);
    }

    /**
     * 根据 Wrapper 条件，查询全部记录
     * <p>注意： 只返回第一个字段的值</p>
     *
     * @param queryWrapper 实体对象封装操作类（可以为 null）
     */
    default Optional<List<Object>> listObjs(Wrapper<T> queryWrapper) {
        List<Object> objects = selectObjs(queryWrapper);
        return CollUtil.isEmpty(objects) ? Optional.empty() : Optional.of(objects);
    }


    // ======================================= 分页 ========================================

    /**
     * 分页
     *
     * @param pageParam    分页参数 需继承PageParam
     * @param queryWrapper queryWrapper
     */
       /* default PageResult<T> selectPage(PageParam pageParam, @Param("ew") Wrapper<T> queryWrapper) {
            // MyBatis Plus 查询
            IPage<T> mpPage = MyBatisUtils.buildPage(pageParam);
            selectPage(mpPage, queryWrapper);
            // 转换返回
            return new PageResult<>(mpPage.getRecords(), mpPage.getTotal(), mpPage.getPages(), mpPage.getCurrent(), mpPage.getSize());
        }*/

    /**
     * 根据 entity 条件，查询全部记录（并翻页）
     *
     * @param sortingFields 排序字段
     * @param pageParam     分页查询条件
     * @param queryWrapper  实体对象封装操作类（可以为 null）
     */
        /*default PageResult<T> selectPage(PageParam pageParam, @Param("ew") Wrapper<T> queryWrapper, Collection<SortingField> sortingFields) {
            // MyBatis Plus 查询
            IPage<T> mpPage = MyBatisUtils.buildPage(pageParam, sortingFields);
            selectPage(mpPage, queryWrapper);
            // 转换返回
            return new PageResult<>(mpPage.getRecords(), mpPage.getTotal(), mpPage.getPages(), mpPage.getCurrent(), mpPage.getSize());
        }*/


}
