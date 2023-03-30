package com.zp.product.controller;

import com.zp.common.utils.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {
    @RequestMapping("/test")
    public Result test(){
        return new Result().ok("ok");
    }
}
