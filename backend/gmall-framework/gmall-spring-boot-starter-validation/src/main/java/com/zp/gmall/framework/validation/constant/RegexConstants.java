package com.zp.gmall.framework.validation.constant;

public interface RegexConstants {

    /**
     * 手机号
     */
    String MOBILE = "^(?:(?:\\+|00)86)?1(?:(?:3[\\d])|(?:4[0,1,4-9])|(?:5[0-3,5-9])|(?:6[2,5-7])|(?:7[0-8])|(?:8[\\d])|(?:9[0-3,5-9]))\\d{8}$";

    /**
     * URL
     */
    String URL = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]$";

    /**
     * 用户名
     */
    String USERNAME = "^[a-zA-Z0-9_]{4,32}$";

    /**
     * 强密码
     */
    String STRONG_PASSWORD =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)"
                    + "(?=.*[~!@#$%^&*()_+]).{8,}$";

}
