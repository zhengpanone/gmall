package com.zp.gmall.module.system.service.auth;

import com.zp.gmall.module.system.controller.admin.auth.dto.AuthLoginDTO;
import com.zp.gmall.module.system.controller.admin.auth.vo.AuthLoginVO;
import com.zp.gmall.module.system.entity.user.AdminUserDO;
import jakarta.validation.Valid;

/**
 * Author : zhengpanone
 * Date : 2025/4/7 18:44
 * Version : v1.0.0
 * Description:
 * 管理后台的认证 Service 接口
 * 提供用户的登录、登出的能力
 */
public interface IAdminAuthService {

    /**
     * 账号登录
     *
     * @param authLoginDTO 登录信息
     * @return 登录结果
     */
    AuthLoginVO login(@Valid AuthLoginDTO authLoginDTO);


    /**
     * 验证账号 + 密码。如果通过，则返回用户
     *
     * @param username 账号
     * @param password 密码
     * @return 用户
     */
    AdminUserDO authenticate(String username, String password);

}
