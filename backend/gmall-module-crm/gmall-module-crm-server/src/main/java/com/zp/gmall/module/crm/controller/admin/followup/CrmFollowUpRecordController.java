package com.zp.gmall.module.crm.controller.admin.followup;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : zhengpanone
 * Date : 2026/5/9 22:34
 * Version : v1.0.0
 * Description: CRM 跟进记录
 */
@Tag(name = "管理后台 - CRM 跟进记录")
@RestController
@RequestMapping("/followup/record")
@RequiredArgsConstructor
@Validated
public class CrmFollowUpRecordController {
}
