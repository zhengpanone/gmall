package com.zp.gmall.module.system.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.gmall.module.system.controller.admin.user.dto.UserDTO;
import com.zp.gmall.module.system.controller.admin.user.vo.AdminUserVO;
import com.zp.gmall.module.system.entity.user.UserDO;
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
public interface IUserService extends IService<UserDO> {

    /**
     * 创建用户
     *
     * @param userDTO 用户信息
     * @return 用户编号
     */
    String createUser(@Valid UserDTO userDTO);


    /**
     * 修改用户
     *
     * @param userDTO 用户信息
     * @return 用户编号
     */
    String updateUser(@Valid UserDTO userDTO);

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
    UserDO getUserByUsername(String username);

    /**
     * 通过用户ID查询用户
     * @param id 用户ID
     * @return 用户对象信息
     */
    AdminUserVO getUserById(String id);
}
