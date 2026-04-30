package com.zp.gmall.module.ai.service.model.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.module.ai.controller.admin.model.dto.AiApiKeyDTO;
import com.zp.gmall.module.ai.controller.admin.model.dto.AiApiKeyPageDTO;
import com.zp.gmall.module.ai.controller.admin.model.vo.AiApiKeyVO;
import com.zp.gmall.module.ai.convert.module.AiApiKeyConvert;
import com.zp.gmall.module.ai.entity.model.AiApiKeyDO;
import com.zp.gmall.module.ai.mapper.model.AiApiKeyMapper;
import com.zp.gmall.module.ai.service.model.IAiApiKeyService;
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
public class AiApiKeyServiceImpl extends ServiceImpl<AiApiKeyMapper, AiApiKeyDO> implements IAiApiKeyService {

    private final AiApiKeyConvert aiApiKeyConvert = Mappers.getMapper(AiApiKeyConvert.class);

    @Override
    public void createApiKey(AiApiKeyDTO dto) {
        AiApiKeyDO aiApiKey = aiApiKeyConvert.convert(dto);
        save(aiApiKey);
    }

    @Override
    public void updateApiKey(AiApiKeyDTO dto) {
        AiApiKeyDO aiApiKey = aiApiKeyConvert.convert(dto);
        updateById(aiApiKey);
    }

    @Override
    public void deleteApiKey(Ids ids) {
        removeByIds(ids.getIds());
    }

    @Override
    public AiApiKeyVO getApiKey(String id) {
        AiApiKeyDO apiKeyDO = getById(id);
        return aiApiKeyConvert.convert(apiKeyDO);
    }

    @Override
    public PageResult<AiApiKeyVO> getAiApiKeyPage(AiApiKeyPageDTO pageDTO) {
        Page<AiApiKeyDO> page = new Page<>(pageDTO.getPageNo(), pageDTO.getPageSize());
        LambdaQueryWrapper<AiApiKeyDO> queryWrapper = Wrappers.<AiApiKeyDO>lambdaQuery()
                .like(StringUtils.isNotBlank(pageDTO.getApiKeyName()), AiApiKeyDO::getName, pageDTO.getApiKeyName());

        Page<AiApiKeyDO> aiApiKeyPage = baseMapper.selectPage(page, queryWrapper);
        List<AiApiKeyVO> aiApiKeyList = aiApiKeyPage.getRecords().stream()
                .map(aiApiKeyConvert::convert)
                .collect(Collectors.toList());
        return PageResult.ok(aiApiKeyPage.getTotal(), aiApiKeyPage.getCurrent(), aiApiKeyPage.getSize(), aiApiKeyList);
    }
}
