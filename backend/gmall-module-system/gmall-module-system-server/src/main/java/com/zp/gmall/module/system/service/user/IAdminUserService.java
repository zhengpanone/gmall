package com.zp.gmall.module.system.service.user;

import com.zp.gmall.module.system.controller.admin.user.dto.UserSaveDTO;
import com.zp.gmall.module.system.controller.admin.user.dto.UserUpdateDTO;
import com.zp.gmall.module.system.controller.admin.user.vo.AdminUserVO;
import com.zp.gmall.module.system.entity.user.AdminUserDO;
import jakarta.validation.Valid;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Author : zhengpanone
 * Date : 2025/3/27 20:26
 * Version : v1.0.0
 * Description:
 */
public interface IAdminUserService {

    /**
     * 创建用户
     *
     * @param userSaveDTO 用户信息
     * @return 用户编号
     */
    Long createUser(@Valid UserSaveDTO userSaveDTO);


    /**
     * 修改用户
     *
     * @param userUpdateDTO 用户信息
     * @return 用户编号
     */
    Long updateUser(@Valid UserUpdateDTO userUpdateDTO);

    /**
     * 获得用户列表
     *
     * @param ids 用户编号数组
     * @return 用户列表
     */
    List<AdminUserVO> getUserListByIds(Collection<? extends Serializable> ids);

    /**
     * 通过用户名查询用户
     *
     * @param username 用户名
     * @return 用户对象信息
     */
    AdminUserDO getUserByUsername(String username);
}
