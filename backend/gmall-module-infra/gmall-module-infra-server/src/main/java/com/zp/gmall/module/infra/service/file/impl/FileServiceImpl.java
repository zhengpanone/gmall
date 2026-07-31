package com.zp.gmall.module.infra.service.file.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.module.infra.controller.admin.file.dto.FileCreateDTO;
import com.zp.gmall.module.infra.controller.admin.file.dto.FilePageDTO;
import com.zp.gmall.module.infra.controller.admin.file.vo.FilePresignedUrlVO;
import com.zp.gmall.module.infra.entity.file.FileDO;
import com.zp.gmall.module.infra.mapper.file.FileMapper;
import com.zp.gmall.module.infra.service.file.IFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;

@Slf4j
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, FileDO> implements IFileService {
    @Override
    public PageResult<FileDO> getFilePage(FilePageDTO configPageDTO) {
        return null;
    }

    @Override
    public FilePresignedUrlVO presignPutUrl(String name, String directory) {
        return null;
    }

    @Override
    public String presignGetUrl(String url, Integer expirationSeconds) {
        return "";
    }


    @Override
    public String createFile(byte[] content, String originalFilename, String directory, String contentType) {
        return "";
    }

    @Override
    public String createFile(FileCreateDTO createDTO) {
        return null;
    }

    @Override
    public void deleteFile(String id) throws Exception {

    }

    @Override
    public void deleteFileList(Collection<? extends Serializable> ids) throws Exception {

    }

    @Override
    public byte[] getFileContent(String configId, String path) throws Exception {
        return new byte[0];
    }

    @Override
    public FileDO getFileByConfigIdAndPath(String configId, String path) {
        return null;
    }
}
