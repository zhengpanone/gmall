package com.zp.module.system.framework.operatelog.core;

import cn.hutool.core.util.StrUtil;
import com.mzt.logapi.service.IParseFunction;

/**
 * Author : zhengpanone
 * Date : 2024/8/29 13:11
 * Version : v1.0.0
 * Description: TODO
 */
public class SexParseFunction implements IParseFunction {
    public static final String NAME = "getSex";

    @Override
    public boolean executeBefore() {
        return true; // 先转换值后对比
    }

    @Override
    public String functionName() {
        return NAME;
    }

    @Override
    public String apply(Object value) {
        if (StrUtil.isEmptyIfStr(value)) {
            return "";
        }
        // TODO
        return "";
        //return DictFrameworkUtils.getDictDataLabel(DictTypeConstants.USER_SEX, value.toString());
    }
}
