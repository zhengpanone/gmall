package com.zp.module.bpm.convert.definition;

import com.zp.module.bpm.controller.admin.definition.dto.BpmCategoryCreateDTO;
import com.zp.module.bpm.controller.admin.definition.dto.BpmCategoryUpdateDTO;
import com.zp.module.bpm.controller.admin.definition.vo.BpmCategoryVO;
import com.zp.module.bpm.entity.definition.BpmCategoryDO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BpmCategoryConvert {

    BpmCategoryDO convert(BpmCategoryCreateDTO createDTO);

    BpmCategoryDO convert(BpmCategoryUpdateDTO updateDTO);

    BpmCategoryVO convert(BpmCategoryDO bpmCategoryDO);
}
