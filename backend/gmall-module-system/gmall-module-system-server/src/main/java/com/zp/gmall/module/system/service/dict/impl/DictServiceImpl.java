package com.zp.gmall.module.system.service.dict.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.module.system.controller.admin.dict.dto.DictSaveDTO;
import com.zp.gmall.module.system.controller.admin.dict.dto.DictUpdateDTO;
import com.zp.gmall.module.system.controller.admin.dict.vo.DictVO;
import com.zp.gmall.module.system.convert.dict.DictConvertMapper;
import com.zp.gmall.module.system.entity.dict.DictDO;
import com.zp.gmall.module.system.mapper.dict.DictMapper;
import com.zp.gmall.module.system.service.dict.IDictService;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, DictDO> implements IDictService {
    private final DictConvertMapper convertMapper = Mappers.getMapper(DictConvertMapper.class);

    @Transactional(rollbackFor = Exception.class)
    @Override
    public DictVO createDict(DictSaveDTO dictDTO) {
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
    public DictVO updateDict(DictUpdateDTO dictDTO) {
        // 检查字典编码是否已存在
        if (checkDictCodeExists(dictDTO.getDictCode(), null)) {
            throw new RuntimeException("字典编码已存在");
        }
        DictDO dict = convertMapper.convert(dictDTO);
        return convertMapper.convert(dict);
    }

    @Override
    public void deleteDict(Ids ids) {
        baseMapper.deleteBatchIds(ids.getIds());
    }
}
