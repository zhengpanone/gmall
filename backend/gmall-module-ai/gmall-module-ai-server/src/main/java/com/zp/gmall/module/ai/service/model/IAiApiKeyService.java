package com.zp.gmall.module.ai.service.model;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.module.ai.controller.admin.model.dto.AiApiKeyDTO;
import com.zp.gmall.module.ai.controller.admin.model.dto.AiApiKeyPageDTO;
import com.zp.gmall.module.ai.controller.admin.model.vo.AiApiKeyVO;
import com.zp.gmall.module.ai.entity.model.AiApiKeyDO;
import jakarta.validation.Valid;

/**
 *
 * Project: backend
 * <p>
 * Module: com.zp.gmall.module.ai.service.model
 * <p>
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-04-30
 */
public interface IAiApiKeyService extends IService<AiApiKeyDO> {
    void createApiKey(@Valid AiApiKeyDTO dto);

    void updateApiKey(@Valid AiApiKeyDTO dto);

    void deleteApiKey(@Valid Ids ids);

    AiApiKeyVO getApiKey(String id);

    PageResult<AiApiKeyVO> getAiApiKeyPage(@Valid AiApiKeyPageDTO dto);
}
