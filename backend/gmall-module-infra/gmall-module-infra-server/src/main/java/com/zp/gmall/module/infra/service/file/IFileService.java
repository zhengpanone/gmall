package com.zp.gmall.module.infra.service.file;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.gmall.module.infra.controller.admin.file.dto.FileCreateDTO;
import com.zp.gmall.module.infra.entity.file.FileDO;
import jakarta.validation.Valid;

public interface IFileService extends IService<FileDO> {
    String createFile(byte[] content, String originalFilename, String directory, String contentType);

    String createFile(@Valid FileCreateDTO createDTO);
}
