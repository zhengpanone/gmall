package com.zp.module.bpm.service.definition;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.module.bpm.controller.admin.definition.dto.BpmCategoryCreateDTO;
import com.zp.module.bpm.controller.admin.definition.dto.BpmCategoryPageDTO;
import com.zp.module.bpm.controller.admin.definition.dto.BpmCategoryUpdateDTO;
import com.zp.module.bpm.controller.admin.definition.vo.BpmCategoryVO;
import com.zp.module.bpm.entity.definition.BpmCategoryDO;
import jakarta.validation.Valid;

public interface IBpmCategoryService extends IService<BpmCategoryDO> {
    /**
     * 创建流程分类
     *
     * @param createDTO 创建信息
     * @return 编号
     */
    String create(@Valid BpmCategoryCreateDTO createDTO);

    /**
     * 更新流程分类
     *
     * @param updateDTO 更新信息
     */
    void update(@Valid BpmCategoryUpdateDTO updateDTO);

    /**
     * 删除流程分类
     * @param ids 编号
     */
    void deleteByIds(@Valid Ids ids);

    /**
     * 通过编号获取流程分类
     * @param id 编号
     * @return 流程分类
     */
    BpmCategoryVO getById(String id);

    PageResult<BpmCategoryVO> getPageList(@Valid BpmCategoryPageDTO pageDTO);
}
