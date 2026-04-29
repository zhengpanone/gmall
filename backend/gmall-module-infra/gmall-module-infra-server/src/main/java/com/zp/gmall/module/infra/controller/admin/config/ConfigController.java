package com.zp.gmall.module.infra.controller.admin.config;

import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.gmall.module.infra.controller.admin.config.dto.ConfigDTO;
import com.zp.gmall.module.infra.controller.admin.config.dto.ConfigPageDTO;
import com.zp.gmall.module.infra.controller.admin.config.vo.ConfigVO;
import com.zp.gmall.module.infra.service.config.IConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.zp.gmall.framework.common.domain.vo.Result.ok;

@Tag(name = "管理后台 - 参数配置")
@RestController
@RequestMapping("/infra/config")
@Validated
@RequiredArgsConstructor
public class ConfigController {

    private final IConfigService configService;

    @PostMapping("/create")
    @Operation(summary = "创建参数配置")
    public Result<?> createConfig(@Valid @RequestBody ConfigDTO configDTO) {
        configService.createConfig(configDTO);
        return ok();
    }

    @PutMapping("/update")
    @Operation(summary = "修改参数配置")
    public Result<?> updateConfig(@Valid @RequestBody ConfigDTO updateReqVO) {
        configService.updateConfig(updateReqVO);
        return ok();
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除参数配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public Result<?> deleteConfig(@RequestParam("id") Ids ids) {
        configService.deleteConfig(ids);
        return ok();
    }

    @GetMapping(value = "/get")
    @Operation(summary = "获得参数配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public Result<ConfigVO> getConfig(@RequestParam("id") String id) {
        return ok(configService.getConfig(id));
    }


    @GetMapping("/page")
    @Operation(summary = "获取参数分页")
    public PageResult<ConfigVO> getDictPage(@Valid ConfigPageDTO dictPageDTO) {
        return configService.getConfigPage(dictPageDTO);
    }
}
