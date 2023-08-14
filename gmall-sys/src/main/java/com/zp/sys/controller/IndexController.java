package com.zp.sys.controller;

import com.zp.common.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页提示
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
public class IndexController {

    @GetMapping("/")
    public Result<String> index() {
        String tips = "你好，gmall-sys已启动，请启动gmall-ui，才能访问页面！";
        return Result.ok(tips);
    }
}
