package com.zp.gmall.module.infra.service.file.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.gmall.module.infra.controller.admin.file.dto.FileCreateDTO;
import com.zp.gmall.module.infra.entity.file.FileDO;
import com.zp.gmall.module.infra.mapper.file.FileMapper;
import com.zp.gmall.module.infra.service.file.IFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, FileDO> implements IFileService {
    @Override
    public String createFile(byte[] content, String originalFilename, String directory, String contentType) {
        return "";
    }

    @Override
    public String createFile(FileCreateDTO createDTO) {
        return null;
    }
}
