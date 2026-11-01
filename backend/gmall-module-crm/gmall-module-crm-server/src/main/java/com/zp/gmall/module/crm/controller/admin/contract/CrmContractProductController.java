package com.zp.gmall.module.crm.controller.admin.contract;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : zhengpanone
 * Date : 2026/5/9 22:32
 * Version : v1.0.0
 * Description: CRM 合同与产品关联
 */
@Tag(name = "管理后台 - CRM 合同与产品关联")
@RestController
@RequestMapping("/contract/product")
@RequiredArgsConstructor
@Validated
public class CrmContractProductController {
}
