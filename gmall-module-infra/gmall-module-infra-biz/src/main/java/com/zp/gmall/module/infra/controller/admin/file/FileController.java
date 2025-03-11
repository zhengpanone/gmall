package com.zp.gmall.module.infra.controller.admin.file;

import com.zp.gmall.framework.common.pojo.Result;
import com.zp.gmall.module.infra.controller.admin.file.dto.PresignDTO;
import com.zp.gmall.module.infra.service.impl.StorageStrategy;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

import static com.zp.gmall.framework.common.pojo.Result.ok;

/**
 * Author : zhengpanone
 * Date : 2025/3/27 13:38
 * Version : v1.0.0
 * Description:
 * https://mp.weixin.qq.com/s/wRv0EsdvOY-TFMxYIApajw
 */

@Tag(name = "管理后台 - 文件存储")
@RestController
@RequestMapping("/infra/file")
@Validated
@Slf4j
@AllArgsConstructor
public class FileController {

    private final StorageStrategy strategy;

    @PostMapping("/upload")
    @Operation(summary = "上传文件", description = "模式一：后端上传文件")
    public Result<?> uploadFile() throws Exception {
        return ok();
    }

    @Operation(summary = "上传凭证",
            description = "根据桶信息，并返回响应结果信息",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "预签名请求参数",
                    content = @Content(schema = @Schema(implementation = PresignDTO.class)),
                    required = true
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "预签名 URL 生成成功",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Result.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "无效的请求参数"
                    )
            }
    )
    @PostMapping("/presign")
    public Map<String, String> presign(@RequestBody PresignDTO presignDTO) {

        S3Presigner presigner = S3Presigner.create();
        if (StringUtils.isEmpty(presignDTO.getBucket())) {
            presignDTO.setBucket("default");
        }
        if (StringUtils.isEmpty(presignDTO.getObjectKey())) {
            presignDTO.setObjectKey(UUID.randomUUID().toString());
        }

        // 2. 创建普通PutObjectRequest
        PutObjectRequest objectRequest = PutObjectRequest
                .builder()
                .bucket(presignDTO.getBucket())
                .key(presignDTO.getObjectKey()).build();

        // 3. 创建预签名请求
        PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder().signatureDuration(Duration.ofMinutes(presignDTO.getExpiryTimeMinutes())).putObjectRequest(objectRequest).build();

        presigner.close();
        return Collections.emptyMap();
    }
}
