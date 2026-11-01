package com.zp.gmall.module.ai.convert.module;

import com.zp.gmall.module.ai.controller.admin.model.dto.AiApiKeyDTO;
import com.zp.gmall.module.ai.controller.admin.model.vo.AiApiKeyVO;
import com.zp.gmall.module.ai.entity.model.AiApiKeyDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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
public interface AiApiKeyConvert {

    AiApiKeyConvert INSTANCE = Mappers.getMapper(AiApiKeyConvert.class);

    @Mapping(source = "apiKeyName", target = "name")
    AiApiKeyDO convert(AiApiKeyDTO aiApiKeyDTO);

    @Mapping(source = "name", target = "name")
    AiApiKeyVO convert(AiApiKeyDO aiApiKey);
}
