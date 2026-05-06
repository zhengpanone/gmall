package com.zp.gmall.module.system.controller.admin.config;

import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.module.system.controller.admin.config.dto.ConfigPageDTO;
import com.zp.gmall.module.system.controller.admin.config.vo.ConfigVO;
import com.zp.gmall.module.system.service.config.IConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/page")
    @Operation(summary = "获取参数分页")
    public PageResult<ConfigVO> getConfigPage(ConfigPageDTO configPageDTO) {
        return configService.getConfigPage(configPageDTO);
    }
}
