package com.zp.gmall.module.ai.controller.admin.model;

import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.gmall.module.ai.controller.admin.model.dto.AiModelDTO;
import com.zp.gmall.module.ai.controller.admin.model.dto.AiModelPageDTO;
import com.zp.gmall.module.ai.controller.admin.model.vo.AiModelVO;
import com.zp.gmall.module.ai.service.model.IAiModelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 *
 * Project: backend
 * <p>
 * Module: com.zp.gmall.module.ai.controller.admin.model.dto
 * <p>
 * Description: AI模型管理
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-04-30
 */
@Tag(name = "AI模型管理", description = "管理后台 - AI模型管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/model")
public class AiModelController {
    private final IAiModelService aiModelService;

    @PostMapping("/create")
    @Operation(summary = "创建AI模型", description = "创建AI模型")
    public Result<?> create(@Valid @RequestBody AiModelDTO dto) {
        aiModelService.create(dto);
        return Result.ok();
    }

    @PutMapping("/update")
    @Operation(summary = "更新AI模型", description = "更新AI模型")
    public Result<?> update(@Valid @RequestBody AiModelDTO dto) {
        aiModelService.update(dto);
        return Result.ok();
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除AI模型", description = "删除AI模型")
    public Result<?> delete(@Valid @RequestBody Ids ids) {
        aiModelService.delete(ids);
        return Result.ok();
    }

    @GetMapping("/get")
    @Operation(summary = "获取AI模型", description = "获取AI模型")
    public Result<?> getById(@Valid @RequestParam("id") String id) {
        AiModelVO model = aiModelService.getById(id);
        return Result.ok(model);
    }

    @PostMapping("/page")
    @Operation(summary = "分页获取AI模型", description = "分页获取AI模型")
    public PageResult<AiModelVO> getPage(@Valid @RequestBody AiModelPageDTO dto) {
        return aiModelService.getPage(dto);
    }


}
