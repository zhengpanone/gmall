package com.zp.gmall.module.system.convert.dict;

import com.zp.gmall.module.system.controller.admin.dict.dto.DictTypeDTO;
import com.zp.gmall.module.system.controller.admin.dict.vo.DictTypeVO;
import com.zp.gmall.module.system.entity.dict.DictTypeDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 字典转换器
 *
 * @author zhengpan
 */
@Mapper(componentModel = "spring")
public interface DictTypeConvertMapper {
    @Mapping(source = "code", target = "typeCode")
    @Mapping(source = "name", target = "typeName")
    DictTypeVO convert(DictTypeDO dictDO);

    @Mapping(source = "typeCode", target = "code")
    @Mapping(source = "typeName", target = "name")
    DictTypeDO convert(DictTypeDTO dto);

}
