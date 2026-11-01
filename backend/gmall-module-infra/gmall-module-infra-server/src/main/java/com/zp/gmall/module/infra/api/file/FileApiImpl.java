package com.zp.gmall.module.infra.api.file;

import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.gmall.module.infra.api.file.dto.FileCreateDTO;
import com.zp.gmall.module.infra.service.file.IFileService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class FileApiImpl implements FileApi {
    @Resource
    private IFileService fileService;
    @Override
    public Result<String> createFile(FileCreateDTO createReqDTO) {
        return null;
    }

    @Override
    public Result<String> presignGetUrl(String url, Integer expirationSeconds) {
        return null;
    }
}
