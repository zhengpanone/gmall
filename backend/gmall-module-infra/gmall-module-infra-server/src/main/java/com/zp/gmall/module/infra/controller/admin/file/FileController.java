package com.zp.gmall.module.infra.controller.admin.file;

import cn.hutool.core.io.IoUtil;
import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.gmall.module.infra.controller.admin.file.dto.FileCreateDTO;
import com.zp.gmall.module.infra.controller.admin.file.dto.FileUploadDTO;
import com.zp.gmall.module.infra.service.file.IFileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static com.zp.gmall.framework.common.domain.vo.Result.ok;

@Tag(name = "管理后台 - 文件存储")
@RestController
@RequiredArgsConstructor
@RequestMapping("/infra/file")
@Validated
@Slf4j
public class FileController {

    @Resource
    private IFileService fileService;

    @PostMapping("/upload")
    @Operation(summary = "上传文件", description = "模式一：后端上传文件")
    @Parameter(name = "file", description = "文件附件", required = true,
            schema = @Schema(type = "string", format = "binary"))
    public Result<String> uploadFile(@Valid FileUploadDTO fileUploadDTO) throws Exception {
        MultipartFile file = fileUploadDTO.getFile();
        byte[] content = IoUtil.readBytes(file.getInputStream());
        return ok(fileService.createFile(content, file.getOriginalFilename(),
                fileUploadDTO.getDirectory(), file.getContentType()));
    }

    @PostMapping("/create")
    @Operation(summary = "创建文件", description = "模式二：前端上传文件：配合 presigned-url 接口，记录上传了上传的文件")
    public Result<String> createFile(@Valid @RequestBody FileCreateDTO createDTO) {
        return ok(fileService.createFile(createDTO));
    }
}
