package com.zp.gmall.module.system.service.user.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.gmall.module.system.controller.admin.user.dto.UserSaveDTO;
import com.zp.gmall.module.system.controller.admin.user.dto.UserUpdateDTO;
import com.zp.gmall.module.system.controller.admin.user.vo.AdminUserVO;
import com.zp.gmall.module.system.convert.user.AdminUserConvertMapper;
import com.zp.gmall.module.system.entity.user.AdminUserDO;
import com.zp.gmall.module.system.mapper.user.AdminUserMapper;
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
public class AdminUserServiceImpl extends ServiceImpl<AdminUserMapper, AdminUserDO> implements IAdminUserService {


    private final AdminUserConvertMapper convertMapper = Mappers.getMapper(AdminUserConvertMapper.class);

    @Override
    public String createUser(UserSaveDTO userSaveDTO) {
        AdminUserDO adminUserDO = convertMapper.convert(userSaveDTO);
        baseMapper.insert(adminUserDO);
        return Convert.toStr(adminUserDO.getId());
    }

    @Override
    public Long updateUser(UserUpdateDTO userUpdateDTO) {
        AdminUserDO adminUserDO = convertMapper.convert(userUpdateDTO);
        baseMapper.updateById(adminUserDO);
        return 0L;
    }

    @Override
    public List<AdminUserVO> getUserListByIds(Collection<? extends Serializable> ids) {
        List<AdminUserDO> adminUserList = baseMapper.selectByIds(ids);
        return convertMapper.convert(adminUserList);
    }

    @Override
    public AdminUserDO getUserByUsername(String username) {
        return baseMapper.selectByUsername(username);
    }

    @Override
    public AdminUserVO getUserById(String id) {
        AdminUserDO adminUserDO = baseMapper.selectById(id);
        return convertMapper.convert(adminUserDO);
    }
}
