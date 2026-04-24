package com.zp.gmall.module.system.service.dict;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.gmall.module.system.controller.admin.dict.dto.DictSaveDTO;
import com.zp.gmall.module.system.controller.admin.dict.vo.DictVO;
import com.zp.gmall.module.system.entity.dict.DictDO;
import jakarta.validation.Valid;

public interface IDictService extends IService<DictDO> {
    DictVO createDict(@Valid DictSaveDTO dictDTO);

    Boolean checkDictCodeExists(String dictCode, String excludeId);
}
