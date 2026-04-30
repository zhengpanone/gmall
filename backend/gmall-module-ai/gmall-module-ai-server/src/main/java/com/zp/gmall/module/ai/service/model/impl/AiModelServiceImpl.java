package com.zp.gmall.module.ai.service.model.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.module.ai.controller.admin.model.dto.AiModelDTO;
import com.zp.gmall.module.ai.controller.admin.model.dto.AiModelPageDTO;
import com.zp.gmall.module.ai.controller.admin.model.vo.AiModelVO;
import com.zp.gmall.module.ai.convert.module.AiModelConvert;
import com.zp.gmall.module.ai.entity.model.AiModelDO;
import com.zp.gmall.module.ai.mapper.model.AiModelMapper;
import com.zp.gmall.module.ai.service.model.IAiModelService;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * Project: backend
 * <p>
 * Module: com.zp.gmall.module.ai.service.model.impl
 * <p>
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-04-30
 */

@Service
public class AiModelServiceImpl extends ServiceImpl<AiModelMapper, AiModelDO> implements IAiModelService {

    private final AiModelConvert aiModelConvert = Mappers.getMapper(AiModelConvert.class);

    @Override
    public void createModel(AiModelDTO dto) {
        AiModelDO aiModelDO = aiModelConvert.convert(dto);
        save(aiModelDO);
    }

    @Override
    public void updateModel(AiModelDTO dto) {
        AiModelDO aiModelDO = aiModelConvert.convert(dto);
        updateById(aiModelDO);
    }

    @Override
    public void deleteModel(Ids ids) {
        removeByIds(ids.getIds());
    }

    @Override
    public AiModelVO getModel(String id) {
        AiModelDO aiModelDO = getById(id);
        return aiModelConvert.convert(aiModelDO);
    }

    @Override
    public PageResult<AiModelVO> getAiModelPage(AiModelPageDTO pageDTO) {
        Page<AiModelDO> page = new Page<>(pageDTO.getPageNo(), pageDTO.getPageSize());
        LambdaQueryWrapper<AiModelDO> queryWrapper = Wrappers.<AiModelDO>lambdaQuery()
                .like(StringUtils.isNotBlank(pageDTO.getName()), AiModelDO::getName, pageDTO.getName());

        Page<AiModelDO> aiModelPage = baseMapper.selectPage(page, queryWrapper);
        List<AiModelVO> aiModelList = aiModelPage.getRecords().stream()
                .map(aiModelConvert::convert)
                .collect(Collectors.toList());
        return PageResult.ok(aiModelPage.getTotal(), aiModelPage.getCurrent(), aiModelPage.getSize(), aiModelList);
    }
}
