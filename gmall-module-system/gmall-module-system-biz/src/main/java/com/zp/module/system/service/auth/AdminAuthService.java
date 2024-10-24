package com.zp.module.system.service.auth;

import com.zp.module.system.controller.admin.auth.dto.AuthLoginDTO;
import com.zp.module.system.controller.admin.auth.vo.AuthLoginVO;
import com.zp.module.system.dal.dataobject.user.AdminUserDO;
import jakarta.validation.Valid;

/**
 * Author : zhengpanone
 * Date : 2023/11/11 17:42
 * Version : v1.0.0
 * Description: 管理后天的认证Service接口
 * 提供用户的登录、登出的能力
 */
public interface AdminAuthService {
    /**
     * 验证码+密码。如果通过则返回用户
     * @param username 账号
     * @param password 密码
     * @return
     */
    AdminUserDO authenticate(String username, String password);
    /**
     * 账号登录
     * @param authLoginDTO 登录信息
     * @return 登录结果
     */
    AuthLoginVO login(@Valid AuthLoginDTO authLoginDTO);
}
