package com.zp.module.bpm.service.definition.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.module.bpm.controller.admin.definition.dto.BpmCategoryCreateDTO;
import com.zp.module.bpm.controller.admin.definition.dto.BpmCategoryPageDTO;
import com.zp.module.bpm.controller.admin.definition.dto.BpmCategoryUpdateDTO;
import com.zp.module.bpm.controller.admin.definition.vo.BpmCategoryVO;
import com.zp.module.bpm.convert.definition.BpmCategoryConvert;
import com.zp.module.bpm.entity.definition.BpmCategoryDO;
import com.zp.module.bpm.mapper.definition.BpmCategoryMapper;
import com.zp.module.bpm.service.definition.IBpmCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BpmCategoryServiceImpl extends ServiceImpl<BpmCategoryMapper, BpmCategoryDO> implements IBpmCategoryService {

    private final BpmCategoryConvert convertMapper = Mappers.getMapper(BpmCategoryConvert.class);

    @Override
    public String create(BpmCategoryCreateDTO createDTO) {
        BpmCategoryDO convert = convertMapper.convert(createDTO);
        baseMapper.insert(convert);
        return Convert.toStr(convert.getId());
    }

    @Override
    public void update(BpmCategoryUpdateDTO updateDTO) {
        BpmCategoryDO convert = convertMapper.convert(updateDTO);
        baseMapper.updateById(convert);
    }

    @Override
    public void deleteByIds(Ids ids) {
        removeByIds(ids.getIds());
    }

    @Override
    public BpmCategoryVO getById(String id) {
        BpmCategoryDO bpmCategoryDO = baseMapper.selectById(id);
        return convertMapper.convert(bpmCategoryDO);
    }

    @Override
    public PageResult<BpmCategoryVO> getPageList(BpmCategoryPageDTO pageDTO) {
        Page<BpmCategoryDO> page = new Page<>(pageDTO.getPageNo(), pageDTO.getPageSize());
        LambdaQueryWrapper<BpmCategoryDO> queryWrapper = Wrappers.<BpmCategoryDO>lambdaQuery().like(StringUtils.isNotBlank(pageDTO.getName()), BpmCategoryDO::getName, pageDTO.getName());
        Page<BpmCategoryDO> pageResult = baseMapper.selectPage(page, queryWrapper);
        List<BpmCategoryVO> voList = pageResult.getRecords().stream().map(convertMapper::convert).collect(Collectors.toList());
        return PageResult.ok(pageResult.getTotal(), pageResult.getCurrent(), pageResult.getSize(), voList);
    }
}
