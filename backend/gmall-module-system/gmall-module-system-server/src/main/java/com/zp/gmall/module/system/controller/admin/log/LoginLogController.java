package com.zp.gmall.module.system.controller.admin.log;

import com.zp.gmall.module.system.service.log.ILoginLogService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-08
 */
@Tag(name = "登录日志管理")
@RestController
@RequestMapping("/log/loginLog")
@RequiredArgsConstructor
@Validated
public class LoginLogController {

    @Resource
    private final ILoginLogService loginLogService;
}
