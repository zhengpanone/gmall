package com.zp.gmall.module.system.controller.admin.notice;

import com.zp.gmall.module.system.service.notice.INoticeService;
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
@Tag(name = "公告管理")
@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
@Validated
public class NoticeController {

    @Resource
    private final INoticeService noticeService;
}
