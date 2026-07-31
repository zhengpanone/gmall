package com.zp.gmall.module.infra.service.file.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.framework.file.core.client.FileClientFactory;
import com.zp.gmall.module.infra.controller.admin.file.dto.FileConfigCreateDTO;
import com.zp.gmall.module.infra.controller.admin.file.dto.FileConfigPageDTO;
import com.zp.gmall.module.infra.controller.admin.file.dto.FileConfigUpdateDTO;
import com.zp.gmall.module.infra.controller.admin.file.vo.FileConfigVO;
import com.zp.gmall.module.infra.entity.file.FileConfigDO;
import com.zp.gmall.module.infra.mapper.file.FileConfigMapper;
import com.zp.gmall.module.infra.service.file.IFileConfigService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class FileConfigServiceImpl extends ServiceImpl<FileConfigMapper, FileConfigDO> implements IFileConfigService {

    @Resource
    private FileClientFactory fileClientFactory;

    @Resource
    private FileConfigMapper fileConfigMapper;

    @Override
    public String createFileConfig(FileConfigCreateDTO createDTO) {
        return "";
    }

    @Override
    public void deleteFileConfig(Ids ids) {

    }

    @Override
    public void updateFileConfig(FileConfigUpdateDTO updateDTO) {

    }

    @Override
    public FileConfigVO getById(String id) {
        return null;
    }

    @Override
    public PageResult<FileConfigVO> getFileConfigPage(FileConfigPageDTO pageDTO) {
        return null;
    }
}
