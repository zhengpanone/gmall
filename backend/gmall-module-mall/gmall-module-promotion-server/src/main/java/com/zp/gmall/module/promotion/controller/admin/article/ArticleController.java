package com.zp.gmall.module.promotion.controller.admin.article;

import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.gmall.module.promotion.controller.admin.article.dto.ArticleDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author : zhengpanone
 * Date : 2026/4/29 23:24
 * Version : v1.0.0
 * Description:
 */
@Tag(name = "管理后台 - 文章管理")
@RestController
@RequestMapping("/article")
@Validated
public class ArticleController {

    @PostMapping("/create")
    @Operation(summary = "创建文章管理")
    public Result<?> createArticle(@Valid @RequestBody ArticleDTO createReqVO) {
        //articleService.createArticle(createReqVO);
        return Result.ok();
    }

    @PutMapping("/update")
    @Operation(summary = "更新文章管理")
    public Result<?> updateArticle(@Valid @RequestBody ArticleDTO updateReqVO) {
        //articleService.updateArticle(updateReqVO);
        return Result.ok();
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除文章管理")
    @Parameter(name = "id", description = "编号", required = true)
    public Result<?> deleteArticle(@RequestParam("id") Long id) {
        //articleService.deleteArticle(id);
        return Result.ok();
    }
}
