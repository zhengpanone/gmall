package com.zp.gmall.module.infra.service.file;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.module.infra.controller.admin.file.dto.FileConfigCreateDTO;
import com.zp.gmall.module.infra.controller.admin.file.dto.FileConfigPageDTO;
import com.zp.gmall.module.infra.controller.admin.file.dto.FileConfigUpdateDTO;
import com.zp.gmall.module.infra.controller.admin.file.vo.FileConfigVO;
import com.zp.gmall.module.infra.entity.file.FileConfigDO;
import jakarta.validation.Valid;

/**
 * 文件配置接口
 */
public interface IFileConfigService extends IService<FileConfigDO> {
    /**
     * 创建文件配置
     * @param createDTO 创建信息
     * @return 文件配置ID
     */
    String createFileConfig(@Valid FileConfigCreateDTO createDTO);
    /**
     * 删除文件配置
     * @param ids 文件配置ID
     */
    void deleteFileConfig(Ids ids);
    /**
     * 更新文件配置
     * @param updateDTO 更新信息
     */
    void updateFileConfig(FileConfigUpdateDTO updateDTO);
    /**
     * 获得文件配置
     * @param id 文件配置ID
     * @return 文件配置
     */
    FileConfigVO getById(String id);
    /**
     * 获得文件配置分页
     * @param pageDTO 分页查询
     * @return 文件配置分页
     */
    PageResult<FileConfigVO> getFileConfigPage(FileConfigPageDTO pageDTO);
}
