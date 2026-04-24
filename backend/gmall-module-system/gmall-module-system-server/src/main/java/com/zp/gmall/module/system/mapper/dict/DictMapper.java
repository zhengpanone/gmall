package com.zp.gmall.module.system.mapper.dict;

import com.zp.gmall.framework.mybatis.core.mapper.BaseMapperX;
import com.zp.gmall.module.system.entity.dict.DictDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DictMapper extends BaseMapperX<DictDO> {
    List<DictDO> selectByDictCode(@Param("dictCode") String dictCode);

    String getDict(@Param("dictCode") String dictCode);

    List<DictDO> selectByDictId(@Param("dictId") String dictId);
}
