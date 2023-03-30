package com.zp.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.common.utils.PageUtils;
import com.zp.sys.entity.User;

import java.util.Map;

/**
 * SysUserService
 *
 * @author zhengpanone
 * @since 2022-07-29
 */
public interface UserService extends IService<User> {
    PageUtils queryPage(Map<String, Object> params);
}
