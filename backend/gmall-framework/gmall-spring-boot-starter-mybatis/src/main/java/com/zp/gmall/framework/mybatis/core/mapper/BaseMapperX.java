package com.zp.gmall.framework.mybatis.core.mapper;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.zp.gmall.framework.common.pojo.PageParam;
import com.zp.gmall.framework.common.pojo.PageResult;
import com.zp.gmall.framework.common.pojo.SortablePageParam;
import com.zp.gmall.framework.common.pojo.SortingField;
import com.zp.gmall.framework.mybatis.core.util.MyBatisUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;

import java.util.Collection;
import java.util.List;

/**
 * Author : zhengpanone
 * Date : 2023/12/20 18:46
 * Version : v1.0.0
 * Description: /**
 * * 在 MyBatis Plus 的 BaseMapper 的基础上拓展，提供更多的能力
 * *
 * 1.{@link BaseMapper} 为 MyBatis Plus 的基础接口，提供基础的 CRUD 能力
 */
public interface BaseMapperX<T> extends BaseMapper<T> {

    Log log = LogFactory.getLog(BaseMapperX.class);


    default PageResult<T> selectPage(SortablePageParam pageParam, @Param("ew") Wrapper<T> queryWrapper) {
        return selectPage(pageParam, pageParam.getSortingFields(), queryWrapper);
    }

    default PageResult<T> selectPage(PageParam pageParam, @Param("ew") Wrapper<T> queryWrapper) {
        return selectPage(pageParam, null, queryWrapper);
    }

    default PageResult<T> selectPage(PageParam pageParam, Collection<SortingField> sortingFields, @Param("ew") Wrapper<T> queryWrapper) {
        // 特殊：不分页，直接查询全部
        if (PageParam.PAGE_SIZE_NONE.equals(pageParam.getPageSize())) {
            List<T> list = selectList(queryWrapper);
            return new PageResult<>(list, (long) list.size());
        }

        // MyBatis Plus 查询
        IPage<T> mpPage = MyBatisUtils.buildPage(pageParam, sortingFields);
        selectPage(mpPage, queryWrapper);
        // 转换返回
        return new PageResult<>(mpPage.getRecords(), mpPage.getTotal());
    }


    default T selectOne(String field, Object value) {
        return selectOne(new QueryWrapper<T>().eq(field, value));
    }

    default T selectOne(SFunction<T, ?> field, Object value) {
        return selectOne(new LambdaQueryWrapper<T>().eq(field, value));
    }

    default T selectOne(String field1, Object value1, String field2, Object value2) {
        return selectOne(new QueryWrapper<T>().eq(field1, value1).eq(field2, value2));
    }

    default T selectOne(SFunction<T, ?> field1, Object value1, SFunction<T, ?> field2, Object value2) {
        return selectOne(new LambdaQueryWrapper<T>().eq(field1, value1).eq(field2, value2));
    }

    default T selectOne(SFunction<T, ?> field1, Object value1, SFunction<T, ?> field2, Object value2,
                        SFunction<T, ?> field3, Object value3) {
        return selectOne(new LambdaQueryWrapper<T>().eq(field1, value1).eq(field2, value2).eq(field3, value3));
    }

    /**
     * 获取满足条件的第 1 条记录
     * <p>
     * 目的：解决并发场景下，插入多条记录后，使用 selectOne 会报错的问题
     *
     * @param field 字段名
     * @param value 字段值
     * @return 实体
     */
    default T selectFirstOne(SFunction<T, ?> field, Object value) {
        // 如果明确使用 MySQL 等场景，可以考虑使用 LIMIT 1 进行优化
        List<T> list = selectList(new LambdaQueryWrapper<T>().eq(field, value));
        return CollUtil.getFirst(list);
    }

    default T selectFirstOne(SFunction<T, ?> field1, Object value1, SFunction<T, ?> field2, Object value2) {
        List<T> list = selectList(new LambdaQueryWrapper<T>().eq(field1, value1).eq(field2, value2));
        return CollUtil.getFirst(list);
    }

    default T selectFirstOne(SFunction<T, ?> field1, Object value1, SFunction<T, ?> field2, Object value2,
                             SFunction<T, ?> field3, Object value3) {
        List<T> list = selectList(new LambdaQueryWrapper<T>().eq(field1, value1).eq(field2, value2).eq(field3, value3));
        return CollUtil.getFirst(list);
    }


    default Long selectCount() {
        return selectCount(new QueryWrapper<>());
    }

    default Long selectCount(String field, Object value) {
        return selectCount(new QueryWrapper<T>().eq(field, value));
    }

    default Long selectCount(SFunction<T, ?> field, Object value) {
        return selectCount(new LambdaQueryWrapper<T>().eq(field, value));
    }

    default List<T> selectList() {
        return selectList(new QueryWrapper<>());
    }

/*    default List<C> selectList(Class<C> clazz) {
        List<T> list = selectList();
        if(CollUtil.isEmpty(list)){
            return CollUtil.newArrayList();
        }
        return MapstructUtils.convertList(list, clazz);
    }*/

    default List<T> selectList(String field, Object value) {
        return selectList(new QueryWrapper<T>().eq(field, value));
    }

    default List<T> selectList(SFunction<T, ?> field, Object value) {
        return selectList(new LambdaQueryWrapper<T>().eq(field, value));
    }

    default List<T> selectList(String field, Collection<?> values) {
        if (CollUtil.isEmpty(values)) {
            return CollUtil.newArrayList();
        }
        return selectList(new QueryWrapper<T>().in(field, values));
    }

    default List<T> selectList(SFunction<T, ?> field, Collection<?> values) {
        if (CollUtil.isEmpty(values)) {
            return CollUtil.newArrayList();
        }
        return selectList(new LambdaQueryWrapper<T>().in(field, values));
    }

    default List<T> selectList(SFunction<T, ?> field1, Object value1, SFunction<T, ?> field2, Object value2) {
        return selectList(new LambdaQueryWrapper<T>().eq(field1, value1).eq(field2, value2));
    }


    default int updateBatch(T update) {
        return update(update, new QueryWrapper<>());
    }

    default Boolean updateBatch(Collection<T> entities) {
        return Db.updateBatchById(entities);
    }

    default Boolean updateBatch(Collection<T> entities, int size) {
        return Db.updateBatchById(entities, size);
    }

    default int delete(String field, String value) {
        return delete(new QueryWrapper<T>().eq(field, value));
    }

    default int delete(SFunction<T, ?> field, Object value) {
        return delete(new LambdaQueryWrapper<T>().eq(field, value));
    }
}
