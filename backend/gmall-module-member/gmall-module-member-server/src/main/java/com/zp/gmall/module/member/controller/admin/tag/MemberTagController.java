package com.zp.gmall.module.member.controller.admin.tag;

import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.gmall.module.member.controller.admin.tag.dto.MemberTagDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author : zhengpanone
 * Date : 2026/4/29 22:07
 * Version : v1.0.0
 * Description:
 */

@Tag(name = "管理后台 - 会员标签")
@RestController
@RequestMapping("/tag")
@Validated
public class MemberTagController {

    @PostMapping("/create")
    @Operation(summary = "创建会员标签")
    public Result<?> createTag(@Valid @RequestBody MemberTagDTO memberTagDTO) {
        //tagService.createTag(createReqVO);
        return Result.ok();
    }

    @PutMapping("/update")
    @Operation(summary = "更新会员标签")
    public Result<?> updateTag(@Valid @RequestBody MemberTagDTO memberTagDTO) {
        //tagService.updateTag(updateReqVO);
        return Result.ok();
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除会员标签")
    @Parameter(name = "id", description = "编号", required = true)
    public Result<?> deleteTag(@RequestParam("id") Long id) {
        //tagService.deleteTag(id);
        return Result.ok();
    }
}
