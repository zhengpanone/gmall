package com.zp.gmall.module.system.convert.dict;

import com.zp.gmall.module.system.controller.admin.dict.dto.DictDataDTO;
import com.zp.gmall.module.system.controller.admin.dict.vo.DictDataVO;
import com.zp.gmall.module.system.entity.dict.DictDataDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * 字典转换器
 *
 * @author zhengpan
 */
@Mapper(componentModel = "spring")
public interface DictDataConvert {

    DictDataConvert INSTANCE = Mappers.getMapper(DictDataConvert.class);

    DictDataVO convert(DictDataDO dictDO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "updater", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "deletedTime", ignore = true)
    DictDataDO convert(DictDataDTO dto);

}
