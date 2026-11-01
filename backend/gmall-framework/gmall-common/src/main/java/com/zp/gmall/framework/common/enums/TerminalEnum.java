package com.zp.gmall.framework.common.enums;

import com.zp.gmall.framework.common.core.Valuable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 终端的枚举
 *
 * @author 芋道源码
 */
@RequiredArgsConstructor
@Getter
public enum TerminalEnum implements Valuable<String> {

    UNKNOWN("0", "未知"), // 目的：在无法解析到 terminal 时，使用它
    WECHAT_MINI_PROGRAM("10", "微信小程序"),
    WECHAT_WAP("11", "微信公众号"),
    H5("20", "H5 网页"),
    APP("31", "手机 App"),
    ;

    /**
     * 终端
     */
    private final String terminal;
    /**
     * 终端名
     */
    private final String name;


    @Override
    public String getValue() {
        return terminal;
    }
}
