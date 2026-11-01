package com.zp.gmall.module.system.service.dict;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.module.system.controller.admin.dict.dto.DictTypeDTO;
import com.zp.gmall.module.system.controller.admin.dict.dto.DictTypePageDTO;
import com.zp.gmall.module.system.controller.admin.dict.vo.DictTypeVO;
import com.zp.gmall.module.system.entity.dict.DictTypeDO;
import jakarta.validation.Valid;

public interface IDictTypeService extends IService<DictTypeDO> {
    /**
     * 创建字典
     *
     * @param dictDTO 字典DTO
     * @return 字典VO
     */
    DictTypeVO create(@Valid DictTypeDTO dictDTO);

    Boolean checkDictCodeExists(String dictCode, String excludeId);

    /**
     * 更新字典
     *
     * @param dictDTO 字典DTO
     * @return 字典VO
     */
    DictTypeVO updateById(@Valid DictTypeDTO dictDTO);

    void delete(@Valid Ids ids);

    PageResult<DictTypeVO> getPageList(@Valid DictTypePageDTO dictPageDTO);

    DictTypeVO getById(String id);
}
