package com.zp.gmall.module.bpm.controller.admin;

import com.zp.gmall.framework.common.pojo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.zp.gmall.framework.common.pojo.Result.ok;

/**
 * Author : zhengpanone
 * Date : 2025/4/8 00:28
 * Version : v1.0.0
 * Description:
 */
@Tag(name = "管理后台 - BPM 流程分类")
@RestController
@RequestMapping("/bpm/category")
@Validated
public class BpmCategoryController {


    @PostMapping("/create")
    @Operation(summary = "创建流程分类")
    public Result<?> createCategory() {
        return ok();
    }
}
