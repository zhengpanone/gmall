package com.zp.framework.mybatis.core.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;

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
public interface BaseMapperPlus<T> extends BaseMapper<T> {

    Log log = LogFactory.getLog(BaseMapperPlus.class);

    /**
     * 使用默认的查询条件查询并返回结果列表
     *
     * @return 返回查询结果的列表
     */
    default List<T> selectList() {
        return this.selectList(new QueryWrapper<>());
    }
}
