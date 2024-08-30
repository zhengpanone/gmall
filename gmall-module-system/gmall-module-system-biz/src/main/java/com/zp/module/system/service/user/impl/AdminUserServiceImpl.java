package com.zp.module.system.service.user.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.annotations.VisibleForTesting;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;
import com.zp.framework.common.enums.CommonStatusEnum;
import com.zp.framework.common.util.collection.CollectionUtils;
import com.zp.framework.common.util.object.BeanUtils;
import com.zp.module.system.controller.admin.user.dto.UserSaveDTO;
import com.zp.module.system.dal.dataobject.post.UserPostDO;
import com.zp.module.system.dal.dataobject.user.AdminUserDO;
import com.zp.module.system.dao.post.UserPostMapper;
import com.zp.module.system.dao.user.AdminUserMapper;
import com.zp.module.system.service.tenant.TenantService;
import com.zp.module.system.service.user.AdminUserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;

import static com.zp.framework.common.exception.util.ServiceExceptionUtils.exception;
import static com.zp.framework.common.util.collection.CollectionUtils.convertList;
import static com.zp.module.system.enums.ErrorCodeConstants.*;
import static com.zp.module.system.enums.LogRecordConstants.*;

/**
 * Author : zhengpanone
 * Date : 2023/12/20 17:35
 * Version : v1.0.0
 * 后台用户 Service 实现类
 */
@Service("adminUserService")
@Slf4j
public class AdminUserServiceImpl extends ServiceImpl<AdminUserMapper, AdminUserDO> implements AdminUserService {
    public static final String USER_INIT_PASSWORD_KEY = "system.user.init-password";
    @Resource
    private AdminUserMapper userMapper;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private TenantService tenantService;
    @Resource
    private UserPostMapper userPostMapper;

    /**
     * 创建用户
     *
     * @param dto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogRecord(type = SYSTEM_USER_TYPE, subType = SYSTEM_USER_CREATE_SUB_TYPE, bizNo = "{{#user.id}}",
            success = SYSTEM_USER_CREATE_SUCCESS)
    public String createUser(UserSaveDTO dto) {
        // TODO
        // 1.1 校验账户配合
        tenantService.handleTenantInfo(tenant -> {
            long count = userMapper.selectCount();
            if (count >= tenant.getAccountCount()) {
                throw exception(USER_COUNT_MAX, tenant.getAccountCount());
            }
        });
        // 1.2 校验正确性
        validateUserForCreateOrUpdate(null, dto.getUsername(),
                dto.getMobile(), dto.getEmail(), dto.getDeptId(), dto.getPostIds());
        // 2.1 插入用户
        AdminUserDO user = BeanUtils.toBean(dto, AdminUserDO.class);
        // 默认开启
        user.setStatus(CommonStatusEnum.ENABLE.getStatus());
        // 加密密码
        user.setPassword(encodePassword(dto.getPassword()));
        userMapper.insert(user);
        // 2.2 插入关联岗位
        if (CollectionUtil.isNotEmpty(user.getPostIds())) {
            userPostMapper.insertBatch(convertList(user.getPostIds(), postId -> new UserPostDO().setUserId(user.getId()).setPostId(postId)));
        }
        // 3. 记录操作日志上下文
        LogRecordContext.putVariable("user", user);
        return user.getId();
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
        return passwordEncoder.matches(rawPassword, encodedPassword);

    }

    /**
     * 更新用户的最后登录信息
     *
     * @param id      用户编号
     * @param loginIp 登录IP
     */
    @Override
    public void updateUserLogin(String id, String loginIp) {
        userMapper.updateById(new AdminUserDO().setId(id).setLoginIp(loginIp).setLoginDate(LocalDateTime.now()));
    }

    /**
     * 通过用户ID查询用户
     *
     * @param id 用户ID
     * @return 用户对象信息
     */
    @Override
    public AdminUserDO getUser(String id) {
        return userMapper.selectById(id);
    }

    /**
     * 对密码进行加密
     *
     * @param password 密码
     * @return 加密后的密码
     */
    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    private AdminUserDO validateUserForCreateOrUpdate(String id, String username, String mobile, String email,
                                                      String deptId, Set<String> postIds) {
        // 关闭数据权限，避免因为没有数据权限，查询不到数据，进而导致唯一校验不正确
        //return DataPermissionUtils.executeIgnore(() -> { TODO
        // 校验用户存在
        AdminUserDO user = validateUserExists(id);
        // 校验用户名唯一
        validateUsernameUnique(id, username);
        // 校验手机号唯一
        validateMobileUnique(id, mobile);
        // 校验邮箱唯一
        validateEmailUnique(id, email);
        // 校验部门处于开启状态
        //deptService.validateDeptList(CollectionUtils.singleton(deptId));
        // 校验岗位处于开启状态
        //postService.validatePostList(postIds);
        return user;
        //});
    }

    @VisibleForTesting
    AdminUserDO validateUserExists(String id) {
        if (id == null) {
            return null;
        }
        AdminUserDO user = userMapper.selectById(id);
        if (user == null) {
            throw exception(USER_NOT_EXISTS);
        }
        return user;
    }

    @VisibleForTesting
    void validateUsernameUnique(String id, String username) {
        if (StrUtil.isBlank(username)) {
            return;
        }
        AdminUserDO user = userMapper.selectByUsername(username);
        if (user == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的用户
        if (id == null) {
            throw exception(USER_USERNAME_EXISTS);
        }
        if (!user.getId().equals(id)) {
            throw exception(USER_USERNAME_EXISTS);
        }
    }

    @VisibleForTesting
    void validateEmailUnique(String id, String email) {
        if (StrUtil.isBlank(email)) {
            return;
        }
        AdminUserDO user = userMapper.selectByEmail(email);
        if (user == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的用户
        if (id == null) {
            throw exception(USER_EMAIL_EXISTS);
        }
        if (!user.getId().equals(id)) {
            throw exception(USER_EMAIL_EXISTS);
        }
    }

    @VisibleForTesting
    void validateMobileUnique(String id, String mobile) {
        if (StrUtil.isBlank(mobile)) {
            return;
        }
        AdminUserDO user = userMapper.selectByMobile(mobile);
        if (user == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的用户
        if (id == null) {
            throw exception(USER_MOBILE_EXISTS);
        }
        if (!user.getId().equals(id)) {
            throw exception(USER_MOBILE_EXISTS);
        }
    }

    /**
     * 校验旧密码
     *
     * @param id          用户 id
     * @param oldPassword 旧密码
     */
    @VisibleForTesting
    void validateOldPassword(Long id, String oldPassword) {
        AdminUserDO user = userMapper.selectById(id);
        if (user == null) {
            throw exception(USER_NOT_EXISTS);
        }
        if (!isPasswordMatch(oldPassword, user.getPassword())) {
            throw exception(USER_PASSWORD_FAILED);
        }
    }

}
