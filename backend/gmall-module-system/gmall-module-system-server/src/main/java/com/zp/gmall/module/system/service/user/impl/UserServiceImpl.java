package com.zp.gmall.module.system.service.user.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.gmall.module.system.controller.admin.user.dto.UserSaveDTO;
import com.zp.gmall.module.system.controller.admin.user.dto.UserUpdateDTO;
import com.zp.gmall.module.system.controller.admin.user.vo.AdminUserVO;
import com.zp.gmall.module.system.convert.user.UserConvertMapper;
import com.zp.gmall.module.system.entity.user.UserDO;
import com.zp.gmall.module.system.mapper.user.UserMapper;
import com.zp.gmall.module.system.service.user.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Author : zhengpanone
 * Date : 2025/3/27 20:30
 * Version : v1.0.0
 * Description:
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements IUserService {


    private final UserConvertMapper convertMapper = Mappers.getMapper(UserConvertMapper.class);

    @Override
    public String createUser(UserSaveDTO userSaveDTO) {
        UserDO userDO = convertMapper.convert(userSaveDTO);
        baseMapper.insert(userDO);
        return Convert.toStr(userDO.getId());
    }

    @Override
    public String updateUser(UserUpdateDTO userUpdateDTO) {
        UserDO userDO = convertMapper.convert(userUpdateDTO);
        baseMapper.updateById(userDO);
        return Convert.toStr(userDO.getId());
    }

    @Override
    public List<AdminUserVO> getUserListByIds(Collection<? extends Serializable> ids) {
        List<UserDO> adminUserList = baseMapper.selectByIds(ids);
        return convertMapper.convert(adminUserList);
    }

    @Override
    public UserDO getUserByUsername(String username) {
        return baseMapper.selectByUsername(username);
    }

    @Override
    public AdminUserVO getUserById(String id) {
        UserDO userDO = baseMapper.selectById(id);
        return convertMapper.convert(userDO);
    }
}
