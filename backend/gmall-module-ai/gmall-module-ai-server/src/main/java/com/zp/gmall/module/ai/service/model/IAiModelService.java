package com.zp.gmall.module.ai.service.model;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.module.ai.controller.admin.model.dto.AiModelDTO;
import com.zp.gmall.module.ai.controller.admin.model.dto.AiModelPageDTO;
import com.zp.gmall.module.ai.controller.admin.model.vo.AiModelVO;
import com.zp.gmall.module.ai.entity.model.AiModelDO;
import jakarta.validation.Valid;

/**
 *
 * Project: backend
 * <p>
 * Module: com.zp.gmall.module.ai.service.model
 * <p>
 * Description: AI模型 Service接口
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-04-30
 */
public interface IAiModelService extends IService<AiModelDO> {

    /**
     * 创建模型
     *
     * @param dto 模型DTO
     */
    void createModel(@Valid AiModelDTO dto);

    /**
     * 更新模型
     *
     * @param dto 模型DTO
     */
    void updateModel(@Valid AiModelDTO dto);

    /**
     * 删除模型
     *
     * @param ids 模型ID
     */
    void deleteModel(@Valid Ids ids);

    /**
     * 获取模型
     *
     * @param id 模型ID
     * @return 模型VO
     */
    AiModelVO getModel(String id);

    /**
     * 获取模型分页
     *
     * @param dto 模型分页DTO
     * @return 模型分页VO
     */
    PageResult<AiModelVO> getAiModelPage(@Valid AiModelPageDTO dto);


}
