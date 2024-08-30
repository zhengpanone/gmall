package com.zp.module.system.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.module.system.controller.admin.user.dto.UserSaveDTO;
import com.zp.module.system.dal.dataobject.user.AdminUserDO;
import jakarta.validation.Valid;

/**
 * Author : zhengpanone
 * Date : 2023/12/20 17:35
 * Version : v1.0.0
 * Description: 后台用户Service接口
 */
public interface AdminUserService extends IService<AdminUserDO> {
    /**
     * 创建用户
     *
     * @param dto
     * @return
     */
    String createUser(@Valid UserSaveDTO dto);

    /**
     * 通过用户名查询用户
     *
     * @param username 用户名
     * @return 用户对象信息
     */
    AdminUserDO getUserByUsername(String username);

    /**
     * 通过手机号获取用户
     *
     * @param mobile 手机号
     * @return 用户对象信息
     */
    AdminUserDO getUserByMobile(String mobile);

    boolean isPasswordMatch(String rawPassword, String encodedPassword);

    /**
     * 更新用户的最后登录信息
     *
     * @param id      用户编号
     * @param loginIp 登录IP
     */
    void updateUserLogin(String id, String loginIp);

    /**
     * 通过用户ID查询用户
     *
     * @param id 用户ID
     * @return 用户对象信息
     */
    AdminUserDO getUser(String id);
}
