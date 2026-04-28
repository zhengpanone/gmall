package com.zp.gmall.module.system.service.dict.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.module.system.controller.admin.dict.dto.DictDTO;
import com.zp.gmall.module.system.controller.admin.dict.dto.DictPageDTO;
import com.zp.gmall.module.system.controller.admin.dict.vo.DictVO;
import com.zp.gmall.module.system.convert.dict.DictConvertMapper;
import com.zp.gmall.module.system.entity.dict.DictDO;
import com.zp.gmall.module.system.mapper.dict.DictMapper;
import com.zp.gmall.module.system.service.dict.IDictService;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.zp.gmall.framework.common.exception.util.ServiceExceptionUtils.exception;
import static com.zp.gmall.module.system.enums.ErrorCodeConstants.ROLE_NOT_EXISTS;

@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, DictDO> implements IDictService {
    private final DictConvertMapper convertMapper = Mappers.getMapper(DictConvertMapper.class);

    @Transactional(rollbackFor = Exception.class)
    @Override
    public DictVO createDict(DictDTO dictDTO) {
        // 检查字典编码是否已存在
        if (checkDictCodeExists(dictDTO.getDictCode(), null)) {
            throw new RuntimeException("字典编码已存在");
        }
        DictDO dict = convertMapper.convert(dictDTO);
        baseMapper.insert(dict);
        return convertMapper.convert(dict);
    }

    @Override
    public Boolean checkDictCodeExists(String dictCode, String excludeId) {
        LambdaQueryWrapper<DictDO> wrapper = new LambdaQueryWrapper<DictDO>()
                .eq(DictDO::getDictCode, dictCode)
                .eq(DictDO::getDeleted, 0);

        if (excludeId != null) {
            wrapper.ne(DictDO::getId, excludeId);
        }

        return count(wrapper) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public DictVO updateDict(DictDTO dictDTO) {
        // 检查字典编码是否已存在
        if (checkDictCodeExists(dictDTO.getDictCode(), null)) {
            throw new RuntimeException("字典编码已存在");
        }
        DictDO dict = convertMapper.convert(dictDTO);
        return convertMapper.convert(dict);
    }

    @Override
    public void deleteDict(Ids ids) {
        baseMapper.deleteByIds(ids.getIds());
    }

    @Override
    public PageResult<DictVO> getDictPage(DictPageDTO dictPageDTO) {
        Page<DictDO> page = new Page<>(dictPageDTO.getPageNo(), dictPageDTO.getPageSize());
        LambdaQueryWrapper<DictDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(dictPageDTO.getName()), DictDO::getDictName, dictPageDTO.getName());
        // 执行分页查询
        IPage<DictDO> dictPage = baseMapper.selectPage(page, queryWrapper);

        // 转换为VO
        List<DictVO> voList = dictPage.getRecords().stream()
                .map(convertMapper::convert)
                .collect(Collectors.toList());

        return PageResult.ok(dictPage.getTotal(), voList);
    }

    @Override
    public DictVO getDictById(String id) {
        DictDO dictDO = baseMapper.selectById(id);
        if (dictDO == null) {
            throw exception(ROLE_NOT_EXISTS);
        }
        return convertMapper.convert(dictDO);
    }
}
