package com.zp.gmall.module.system.service.dict;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.module.system.controller.admin.dict.dto.DictSaveDTO;
import com.zp.gmall.module.system.controller.admin.dict.dto.DictUpdateDTO;
import com.zp.gmall.module.system.controller.admin.dict.vo.DictVO;
import com.zp.gmall.module.system.entity.dict.DictDO;
import jakarta.validation.Valid;

public interface IDictService extends IService<DictDO> {
    /**
     * 创建字典
     * @param dictDTO 字典DTO
     * @return 字典VO
     */
    DictVO createDict(@Valid DictSaveDTO dictDTO);

    Boolean checkDictCodeExists(String dictCode, String excludeId);

    /**
     * 更新字典
     * @param dictDTO 字典DTO
     * @return 字典VO
     */
    DictVO updateDict(@Valid DictUpdateDTO dictDTO);

    void deleteDict(@Valid Ids ids);
}
