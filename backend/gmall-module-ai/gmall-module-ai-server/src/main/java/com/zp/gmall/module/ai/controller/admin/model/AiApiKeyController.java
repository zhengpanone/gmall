package com.zp.gmall.module.ai.controller.admin.model;

import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.gmall.module.ai.controller.admin.model.dto.AiApiKeyDTO;
import com.zp.gmall.module.ai.controller.admin.model.dto.AiApiKeyPageDTO;
import com.zp.gmall.module.ai.controller.admin.model.vo.AiApiKeyVO;
import com.zp.gmall.module.ai.service.model.IAiApiKeyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 *
 * Project: backend
 * <p>
 * Module: com.zp.gmall.module.ai.controller.admin.model
 * <p>
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-04-30
 */
@Tag(name = "AI API密钥", description = "管理后台 - AI API密钥")
@RestController
@RequestMapping("/api-key")
@Validated
@RequiredArgsConstructor
public class AiApiKeyController {

    private final IAiApiKeyService aiApiKeyService;

    @PostMapping("/create")
    @Operation(summary = "创建API密钥", description = "创建API密钥")
    public Result<?> createApiKey(@Valid @RequestBody AiApiKeyDTO dto) {
        aiApiKeyService.createApiKey(dto);
        return Result.ok();
    }

    @PutMapping("/update")
    @Operation(summary = "更新API密钥", description = "更新API密钥")
    public Result<?> updateApiKey(@Valid @RequestBody AiApiKeyDTO dto) {
        aiApiKeyService.updateApiKey(dto);
        return Result.ok();
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除API密钥", description = "删除API密钥")
    public Result<?> deleteApiKey(@Valid @RequestBody Ids ids) {
        aiApiKeyService.deleteApiKey(ids);
        return Result.ok();
    }

    @GetMapping("/get")
    @Parameter(name = "id", description = "API密钥ID", required = true, example = "1")
    @Operation(summary = "获取API密钥", description = "获取API密钥")
    public Result<AiApiKeyVO> getApiKey(@Valid @RequestParam("id") String id) {
        AiApiKeyVO apiKeyVO = aiApiKeyService.getApiKey(id);
        return Result.ok(apiKeyVO);
    }

    @PostMapping("/page")
    @Operation(summary = "获取字典分页")
    public PageResult<AiApiKeyVO> getAiApiKeyPage(@Valid @RequestBody AiApiKeyPageDTO aiApiKeyPageDTO) {
        return aiApiKeyService.getAiApiKeyPage(aiApiKeyPageDTO);
    }
}
