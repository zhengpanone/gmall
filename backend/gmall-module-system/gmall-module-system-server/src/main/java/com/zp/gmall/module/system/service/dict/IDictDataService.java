package com.zp.gmall.module.system.service.dict;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.gmall.module.system.controller.admin.dict.dto.DictDataDTO;
import com.zp.gmall.module.system.controller.admin.dict.dto.DictDataQueryDTO;
import com.zp.gmall.module.system.controller.admin.dict.vo.DictDataVO;
import com.zp.gmall.module.system.entity.dict.DictDataDO;
import jakarta.validation.Valid;

import java.util.List;

public interface IDictDataService extends IService<DictDataDO> {
    /**
     * 创建字典
     *
     * @param dictDataDTO 字典DTO
     * @return 字典VO
     */
    DictDataVO createDictData(@Valid DictDataDTO dictDataDTO);

    Boolean checkDictCodeExists(String typeCode, String dataCode, String excludeId);

    /**
     * 更新字典
     *
     * @param dictDataDTO 字典DTO
     * @return 字典VO
     */
    DictDataVO updateDictData(@Valid DictDataDTO dictDataDTO);

    void deleteDictData(@Valid Ids ids);

    Result<List<DictDataVO>> getDictDataList(@Valid DictDataQueryDTO dataQueryDTO);

    DictDataVO getDictDataById(String id);
}
