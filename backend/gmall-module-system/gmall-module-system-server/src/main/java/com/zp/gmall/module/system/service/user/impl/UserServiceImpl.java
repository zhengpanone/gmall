package com.zp.gmall.module.system.service.user.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.gmall.framework.common.enums.CommonStatusEnum;
import com.zp.gmall.module.system.controller.admin.user.dto.UserDTO;
import com.zp.gmall.module.system.controller.admin.user.vo.AdminUserVO;
import com.zp.gmall.module.system.convert.user.UserConvert;
import com.zp.gmall.module.system.entity.user.UserDO;
import com.zp.gmall.module.system.mapper.user.UserMapper;
import com.zp.gmall.module.system.service.user.IUserService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author : zhengpanone
 * Date : 2025/3/27 20:30
 * Version : v1.0.0
 * Description:
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements IUserService {

    private final UserConvert convertMapper = Mappers.getMapper(UserConvert.class);
    @Resource
    private final PasswordEncoder passwordEncoder;


    @Override
    public String create(UserDTO userDTO) {
        UserDO userDO = convertMapper.convert(userDTO);
        userDO.setStatus(CommonStatusEnum.ENABLE.getValue());
        userDO.setPassword(passwordEncoder.encode(userDO.getPassword()));
        baseMapper.insert(userDO);
        return Convert.toStr(userDO.getId());
    }

    @Override
    public String update(UserDTO userUpdateDTO) {
        UserDO userDO = convertMapper.convert(userUpdateDTO);
        baseMapper.updateById(userDO);
        return Convert.toStr(userDO.getId());
    }

    @Override
    public List<AdminUserVO> getByIds(Collection<? extends Serializable> ids) {
        List<UserDO> adminUserList = baseMapper.selectByIds(ids);
        return convertMapper.convert(adminUserList);
    }

    @Override
    public UserDO getUserByUsername(String username) {
        return baseMapper.selectByUsername(username);
    }

    @Override
    public AdminUserVO getById(String id) {
        UserDO userDO = baseMapper.selectById(id);
        return convertMapper.convert(userDO);
    }

    /**
     * 批量插入（性能优于逐条插入）
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchCreate(List<UserDTO> dtoList) {
        List<UserDO> users = dtoList.stream()
                .map(convertMapper::convert)
                .collect(Collectors.toList());
        saveBatch(users, 500); // 每批 500 条
    }

    @Override
    public boolean verifyPassword(String rawPassword, String encodePassword) {
        return passwordEncoder.matches(rawPassword, encodePassword);
    }
}
