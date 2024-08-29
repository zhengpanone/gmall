package com.zp.framework.security.core.rpc;

import com.zp.framework.common.util.json.JsonUtils;
import com.zp.framework.security.core.LoginUser;
import com.zp.framework.security.core.util.SecurityFrameworkUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Author : zhengpanone
 * Date : 2024/8/6 16:47
 * Version : v1.0.0
 * Description: LoginUser 的 RequestInterceptor 实现类：Feign 请求时，将 {@link LoginUser} 设置到 header 中，继续透传给被调用的服务
 */
@Slf4j
public class LoginUserRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        LoginUser user = SecurityFrameworkUtils.getLoginUser();
        if (user == null) {
            return;
        }
        try {
            String userStr = JsonUtils.toJsonString(user);
            userStr = URLEncoder.encode(userStr, StandardCharsets.UTF_8); // 编码，避免中文乱码
            requestTemplate.header(SecurityFrameworkUtils.LOGIN_USER_HEADER, userStr);
        } catch (Exception ex) {
            log.error("[apply][序列化 LoginUser({}) 发生异常]", user, ex);
            throw ex;
        }
    }
}
