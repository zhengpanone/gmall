package com.zp.module.system.service.user.impl;

import com.zp.module.system.controller.admin.user.dto.UserSaveDTO;
import com.zp.module.system.dal.dataobject.user.AdminUserDO;
import com.zp.module.system.dao.user.AdminUserMapper;
import com.zp.module.system.service.user.AdminUserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Author : zhengpanone
 * Date : 2023/12/20 17:35
 * Version : v1.0.0
 */
@Service("adminUserService")
@Slf4j
public class AdminUserServiceImpl implements AdminUserService {
    @Resource
    private AdminUserMapper userMapper;
    //@Resource
    //private PasswordEncoder passwordEncoder;

    /**
     * 创建用户
     *
     * @param dto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createUser(UserSaveDTO dto) {
        return null;
    }

    /**
     * 通过用户名查询用户
     *
     * @param username 用户名
     * @return 用户对象信息
     */
    @Override
    public AdminUserDO getUserByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    /**
     * 通过手机号获取用户
     *
     * @param mobile 手机号
     * @return 用户对象信息
     */
    @Override
    public AdminUserDO getUserByMobile(String mobile) {
        return userMapper.selectByMobile(mobile);
    }

    @Override
    public boolean isPasswordMatch(String rawPassword, String encodedPassword) {
        //return passwordEncoder.matches(rawPassword, encodedPassword);
        return true;
    }

    /**
     * 对密码进行加密
     *
     * @param password 密码
     * @return 加密后的密码
     */
    private String encodePassword(String password) {
        return null;/*passwordEncoder.encode(password);*/
    }
}
