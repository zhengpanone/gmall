package com.zp.gmall.module.system.convert.dict;

import com.zp.gmall.module.system.controller.admin.dict.dto.DictDataDTO;
import com.zp.gmall.module.system.controller.admin.dict.vo.DictDataVO;
import com.zp.gmall.module.system.entity.dict.DictDataDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 字典转换器
 *
 * @author zhengpan
 */
@Mapper(componentModel = "spring")
public interface DictDataConvertMapper {
    DictDataVO convert(DictDataDO dictDO);

    @Mapping(target = "id", ignore = true)
    DictDataDO convert(DictDataDTO dto);

}
