package com.zp.sys.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController
 *
 * @author zhengpanone
 * @since 2022-06-30
 */
@RestController
@RequestMapping("/sys")
public class UserController {
    @GetMapping("/test")
    public String test() {
        return "11111";
    }

    @PostMapping("/login")
    public String login() {
        return "登录";
    }
}
