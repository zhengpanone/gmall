package com.zp.gmall.module.system.service.dict;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.module.system.controller.admin.dict.dto.DictDTO;
import com.zp.gmall.module.system.controller.admin.dict.dto.DictPageDTO;
import com.zp.gmall.module.system.controller.admin.dict.vo.DictVO;
import com.zp.gmall.module.system.entity.dict.DictDO;
import jakarta.validation.Valid;

public interface IDictService extends IService<DictDO> {
    /**
     * 创建字典
     * @param dictDTO 字典DTO
     * @return 字典VO
     */
    DictVO createDict(@Valid DictDTO dictDTO);

    Boolean checkDictCodeExists(String dictCode, String excludeId);

    /**
     * 更新字典
     * @param dictDTO 字典DTO
     * @return 字典VO
     */
    DictVO updateDict(@Valid DictDTO dictDTO);

    void deleteDict(@Valid Ids ids);

    PageResult<DictVO> getDictPage(@Valid DictPageDTO dictPageDTO);

    DictVO getDictById(String id);
}
