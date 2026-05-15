package com.zp.gmall.module.system.controller.admin.oauth2;

import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.gmall.module.system.controller.admin.oauth2.dto.OAuth2ClientDTO;
import com.zp.gmall.module.system.service.oauth2.IOAuth2ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-15
 */
@Tag(name = "管理后台 - OAuth2 客户端管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth2-client")
@Validated
public class OAuth2ClientController {

    private final IOAuth2ClientService oAuth2ClientService;

    @PostMapping("/create")
    @Operation(summary = "创建 OAuth2 客户端")
    public Result<?> create(@Validated @RequestBody OAuth2ClientDTO dto) {
        oAuth2ClientService.create(dto);
        return Result.ok();
    }
}
