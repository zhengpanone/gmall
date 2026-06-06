package com.zp.gmall.module.system.controller.admin.config;

import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.gmall.module.system.controller.admin.config.dto.ConfigDTO;
import com.zp.gmall.module.system.controller.admin.config.dto.ConfigPageDTO;
import com.zp.gmall.module.system.controller.admin.config.vo.ConfigVO;
import com.zp.gmall.module.system.service.config.IConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 *
 * Project: backend
 * <p>
 * Module: com.zp.gmall.module.system.controller.admin.config
 * <p>
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-06
 */
@Tag(name = "管理后台 - 参数管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/config")
public class ConfigController {

    private final IConfigService configService;

    @GetMapping("/page")
    @Operation(summary = "获取参数分页")
    public PageResult<ConfigVO> getPageList(ConfigPageDTO configPageDTO) {
        return configService.getConfigPage(configPageDTO);
    }

    @PostMapping("/create")
    @Operation(summary = "创建参数")
    public Result<?> create(@RequestBody ConfigDTO configDTO) {
        return Result.ok(configService.createConfig(configDTO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新参数")
    public Result<?> updateById(@RequestBody ConfigDTO configDTO) {
        return Result.ok(configService.updateConfig(configDTO));
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除参数")
    public Result<?> deleteByIds(@RequestBody Ids ids) {
        configService.deleteConfig(ids);
        return Result.ok();
    }

    @GetMapping("/get")
    @Operation(summary = "获取参数详情")
    public Result<ConfigVO> getById(@Parameter(description = "角色ID", required = true, example = "1")
                                    @RequestParam("id") String id) {
        return Result.ok(configService.getById(id));
    }

    @PostMapping("/anonymous/getKeys")
    @Operation(summary = "匿名根据key数组获取配置")
    public Result<Map<String, ConfigVO>> getByKeys(@RequestBody List<String> keys) {
        return Result.ok(configService.getByKeys(keys));
    }

    @GetMapping("/getByKey")
    @Operation(summary = "获取参数详情")
    public Result<ConfigVO> getByKey(@Parameter(description = "角色ID", required = true, example = "1")
                                     @RequestParam("configKey") String configKey) {
        return Result.ok(configService.getByKey(configKey));
    }

}
