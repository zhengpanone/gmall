package com.zp.gmall.module.crm.controller.admin.product;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : zhengpanone
 * Date : 2026/5/9 22:24
 * Version : v1.0.0
 * Description:
 */
@Tag(name = "管理后台 - CRM 产品")
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Validated
public class CrmProductController {
}
