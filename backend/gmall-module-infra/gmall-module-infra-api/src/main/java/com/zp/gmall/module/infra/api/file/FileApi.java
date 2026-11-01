package com.zp.gmall.module.infra.api.file;

import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.gmall.module.infra.api.file.dto.FileCreateDTO;
import com.zp.gmall.module.infra.constant.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = ApiConstants.NAME)
@Tag(name = "文件服务")
public interface FileApi {
    String PREFIX = ApiConstants.PREFIX + "/files";

    /**
     * 保存文件，并返回文件的访问路径
     *
     * @param content 文件内容
     * @return 文件路径
     */
    default String createFile(byte[] content) {
        return createFile(content, null, null, null);
    }

    /**
     * 保存文件，并返回文件的访问路径
     *
     * @param content 文件内容
     * @param name    文件名称，允许空
     * @return 文件路径
     */
    default String createFile(byte[] content, String name) {
        return createFile(content, name, null, null);
    }

    /**
     * 保存文件，并返回文件的访问路径
     *
     * @param content   文件内容
     * @param fileName  文件名称，允许空
     * @param directory 目录，允许空
     * @param type      文件的 MIME 类型，允许空
     * @return 文件路径
     */
    default String createFile(@NotEmpty(message = "文件内容不能为空") byte[] content, String fileName, String directory, String type) {
        return createFile(new FileCreateDTO(content, fileName, directory, type)).getCheckedData();
    }

    @PostMapping(PREFIX + "/create")
    @Operation(summary = "保存文件，并返回文件的访问路径")
    Result<String> createFile(@Valid @RequestBody FileCreateDTO createReqDTO);

    /**
     * 生成文件预签名地址，用于读取
     *
     * @param url               完整的文件访问地址
     * @param expirationSeconds 访问有效期，单位秒
     * @return 文件预签名地址
     */
    @GetMapping(PREFIX + "/presigned-url")
    @Operation(summary = "生成文件预签名地址，用于读取")
    Result<String> presignGetUrl(@NotEmpty(message = "URL 不能为空") @RequestParam("url") String url,
                                 Integer expirationSeconds);
}
