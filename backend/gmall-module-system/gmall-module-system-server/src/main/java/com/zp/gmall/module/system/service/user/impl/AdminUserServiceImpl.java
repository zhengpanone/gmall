package com.zp.gmall.module.system.service.user.impl;

import cn.hutool.core.convert.Convert;
import com.zp.gmall.module.system.controller.admin.user.dto.UserSaveDTO;
import com.zp.gmall.module.system.controller.admin.user.dto.UserUpdateDTO;
import com.zp.gmall.module.system.controller.admin.user.vo.AdminUserVO;
import com.zp.gmall.module.system.convert.user.AdminUserConvertMapper;
import com.zp.gmall.module.system.entity.user.AdminUserDO;
import com.zp.gmall.module.system.mapper.AdminUserMapper;
import com.zp.gmall.module.system.service.user.IAdminUserService;
import jakarta.annotation.Resource;
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
public class AdminUserServiceImpl implements IAdminUserService {
    @Resource
    private AdminUserMapper userMapper;

    private final AdminUserConvertMapper convertMapper = Mappers.getMapper(AdminUserConvertMapper.class);

    @Override
    public Long createUser(UserSaveDTO userSaveDTO) {
        AdminUserDO adminUserDO = convertMapper.convert(userSaveDTO);
        userMapper.insert(adminUserDO);
        return Convert.toLong(adminUserDO.getId());
    }

    @Override
    public Long updateUser(UserUpdateDTO userUpdateDTO) {
        AdminUserDO adminUserDO = convertMapper.convert(userUpdateDTO);
        userMapper.updateById(adminUserDO);
        return 0L;
    }

    @Override
    public List<AdminUserVO> getUserListByIds(Collection<? extends Serializable> ids) {
        List<AdminUserDO> adminUserList = userMapper.selectByIds(ids);
        return convertMapper.convert(adminUserList);
    }

    @Override
    public AdminUserDO getUserByUsername(String username) {
        return userMapper.selectByUsername(username);
    }
}
