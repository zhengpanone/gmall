package com.zp.gmall.module.crm.controller.admin.receivable;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : zhengpanone
 * Date : 2026/5/9 22:34
 * Version : v1.0.0
 * Description: 回款
 */
@Tag(name = "管理后台 - CRM 回款")
@RestController
@RequestMapping("/receivable")
@RequiredArgsConstructor
@Validated
public class CrmReceivableController {
}
