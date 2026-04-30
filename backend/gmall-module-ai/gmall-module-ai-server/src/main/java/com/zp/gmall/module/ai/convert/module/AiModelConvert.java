package com.zp.gmall.module.ai.convert.module;

import com.zp.gmall.module.ai.controller.admin.model.dto.AiModelDTO;
import com.zp.gmall.module.ai.controller.admin.model.vo.AiModelVO;
import com.zp.gmall.module.ai.entity.model.AiModelDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 *
 * Project: backend
 * <p>
 * Module: com.zp.gmall.module.ai.convert.module
 * <p>
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-04-30
 */
@Mapper(componentModel = "spring")
public interface AiModelConvert {

    AiModelConvert INSTANCE = Mappers.getMapper(AiModelConvert.class);

    AiModelDO convert(AiModelDTO aiModelDTO);

    AiModelVO convert(AiModelDO aiModelDO);
}
