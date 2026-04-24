package com.zp.gmall.module.system.convert.dict;

import com.zp.gmall.module.system.controller.admin.dict.dto.DictSaveDTO;
import com.zp.gmall.module.system.controller.admin.dict.vo.DictVO;
import com.zp.gmall.module.system.entity.dict.DictDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 字典转换器
 *
 * @author zhengpan
 */
@Mapper(componentModel = "spring")
public interface DictConvertMapper {
    DictVO convert(DictDO dictDO);

    @Mapping(target = "id", ignore = true)
    DictDO convert(DictSaveDTO dto);
}
