package com.zp.gmall.module.system.service.dict.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.module.system.controller.admin.dict.dto.DictTypeDTO;
import com.zp.gmall.module.system.controller.admin.dict.dto.DictTypePageDTO;
import com.zp.gmall.module.system.controller.admin.dict.vo.DictTypeVO;
import com.zp.gmall.module.system.convert.dict.DictTypeConvert;
import com.zp.gmall.module.system.entity.dict.DictTypeDO;
import com.zp.gmall.module.system.mapper.dict.DictTypeMapper;
import com.zp.gmall.module.system.service.dict.IDictTypeService;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.zp.gmall.framework.common.exception.util.ServiceExceptionUtils.exception;
import static com.zp.gmall.module.system.enums.ErrorCodeConstants.ROLE_NOT_EXISTS;

@Service
public class DictTypeServiceImpl extends ServiceImpl<DictTypeMapper, DictTypeDO> implements IDictTypeService {
    private final DictTypeConvert convertMapper = Mappers.getMapper(DictTypeConvert.class);

    @Transactional(rollbackFor = Exception.class)
    @Override
    public DictTypeVO createDictType(DictTypeDTO dictDTO) {
        // 检查字典编码是否已存在
        if (checkDictCodeExists(dictDTO.getTypeCode(), null)) {
            throw new RuntimeException("字典编码已存在");
        }
        DictTypeDO dict = convertMapper.convert(dictDTO);
        baseMapper.insert(dict);
        return convertMapper.convert(dict);
    }

    @Override
    public Boolean checkDictCodeExists(String dictCode, String excludeId) {
        LambdaQueryWrapper<DictTypeDO> wrapper = new LambdaQueryWrapper<DictTypeDO>()
                .eq(DictTypeDO::getCode, dictCode)
                .eq(DictTypeDO::getDeleted, 0)
                .ne(StringUtils.isNotBlank(excludeId), DictTypeDO::getId, excludeId);
        return count(wrapper) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public DictTypeVO updateDict(DictTypeDTO dictDTO) {
        // 检查字典编码是否已存在
        if (checkDictCodeExists(dictDTO.getTypeCode(), dictDTO.getId())) {
            throw new RuntimeException("字典编码已存在");
        }
        DictTypeDO dict = convertMapper.convert(dictDTO);
        return convertMapper.convert(dict);
    }

    @Override
    public void deleteDict(Ids ids) {
        baseMapper.deleteByIds(ids.getIds());
    }

    @Override
    public PageResult<DictTypeVO> getDictPage(DictTypePageDTO dictPageDTO) {
        Page<DictTypeDO> page = new Page<>(dictPageDTO.getPageNo(), dictPageDTO.getPageSize());
        LambdaQueryWrapper<DictTypeDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(dictPageDTO.getTypeName()), DictTypeDO::getName, dictPageDTO.getTypeName());
        // 执行分页查询
        IPage<DictTypeDO> dictPage = baseMapper.selectPage(page, queryWrapper);

        // 转换为VO
        List<DictTypeVO> voList = dictPage.getRecords().stream()
                .map(convertMapper::convert)
                .collect(Collectors.toList());

        return PageResult.ok(dictPage.getTotal(), voList);
    }

    @Override
    public DictTypeVO getDictById(String id) {
        DictTypeDO dictDO = baseMapper.selectById(id);
        if (dictDO == null) {
            throw exception(ROLE_NOT_EXISTS);
        }
        return convertMapper.convert(dictDO);
    }
}
