package com.zp.module.system.framework.operatelog.core;

import com.mzt.logapi.service.IParseFunction;
import com.zp.module.system.service.dept.DeptService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Author : zhengpanone
 * Date : 2024/8/29 12:29
 * Version : v1.0.0
 * Description: 部门名字的 {@link IParseFunction} 实现类
 */
@Slf4j
@Component
public class DeptParseFunction implements IParseFunction {
    public static final String NAME = "getDeptById";

    @Resource
    private DeptService deptService;
    @Override
    public String functionName() {
        return NAME;
    }

    @Override
    public String apply(Object value) {
        return "";
    }
}
