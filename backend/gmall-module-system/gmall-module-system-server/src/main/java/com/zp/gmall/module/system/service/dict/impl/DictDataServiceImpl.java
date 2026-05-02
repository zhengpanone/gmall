package com.zp.gmall.module.system.service.dict.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.gmall.module.system.controller.admin.dict.dto.DictDataDTO;
import com.zp.gmall.module.system.controller.admin.dict.dto.DictDataQueryDTO;
import com.zp.gmall.module.system.controller.admin.dict.vo.DictDataVO;
import com.zp.gmall.module.system.convert.dict.DictDataConvertMapper;
import com.zp.gmall.module.system.entity.dict.DictDataDO;
import com.zp.gmall.module.system.mapper.dict.DictDataMapper;
import com.zp.gmall.module.system.service.dict.IDictDataService;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.zp.gmall.framework.common.exception.util.ServiceExceptionUtils.exception;
import static com.zp.gmall.module.system.enums.ErrorCodeConstants.ROLE_NOT_EXISTS;

@Service
public class DictDataServiceImpl extends ServiceImpl<DictDataMapper, DictDataDO> implements IDictDataService {

    private final DictDataConvertMapper convertMapper = Mappers.getMapper(DictDataConvertMapper.class);

    @Transactional(rollbackFor = Exception.class)
    @Override
    public DictDataVO createDictData(DictDataDTO dictDataDTO) {
        // 检查字典编码是否已存在
        if (checkDictCodeExists(dictDataDTO.getTypeCode(), dictDataDTO.getDataCode(), null)) {
            throw new RuntimeException("字典编码已存在");
        }
        DictDataDO dict = convertMapper.convert(dictDataDTO);
        baseMapper.insert(dict);
        return convertMapper.convert(dict);
    }

    @Override
    public Boolean checkDictCodeExists(String typeCode, String dataCode, String excludeId) {
        LambdaQueryWrapper<DictDataDO> wrapper = new LambdaQueryWrapper<DictDataDO>()
                .eq(DictDataDO::getDataCode, dataCode)
                .eq(DictDataDO::getTypeCode, typeCode)
                .eq(DictDataDO::getDeleted, 0);

        if (excludeId != null) {
            wrapper.ne(DictDataDO::getId, excludeId);
        }

        return count(wrapper) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public DictDataVO updateDictData(DictDataDTO dictDTO) {
        // 检查字典编码是否已存在
        if (checkDictCodeExists(dictDTO.getTypeCode(), dictDTO.getDataCode(), null)) {
            throw new RuntimeException("字典编码已存在");
        }
        DictDataDO dict = convertMapper.convert(dictDTO);
        return convertMapper.convert(dict);
    }

    @Override
    public void deleteDictData(Ids ids) {
        baseMapper.deleteByIds(ids.getIds());
    }

    @Override
    public Result<List<DictDataVO>> getDictDataList(DictDataQueryDTO dataQueryDTO) {

        LambdaQueryWrapper<DictDataDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(dataQueryDTO.getTypeCode()), DictDataDO::getTypeCode, dataQueryDTO.getTypeCode());
        queryWrapper.eq(StringUtils.isNotBlank(dataQueryDTO.getDataName()), DictDataDO::getDataName, dataQueryDTO.getDataName());
        // 执行分页查询
        List<DictDataDO> dataList = baseMapper.selectList(queryWrapper);

        // 转换为VO
        List<DictDataVO> voList = dataList.stream()
                .map(convertMapper::convert)
                .collect(Collectors.toList());

        return Result.ok(voList);
    }

    @Override
    public DictDataVO getDictDataById(String id) {
        DictDataDO dictDO = baseMapper.selectById(id);
        if (dictDO == null) {
            throw exception(ROLE_NOT_EXISTS);
        }
        return convertMapper.convert(dictDO);
    }
}
