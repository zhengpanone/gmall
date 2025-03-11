package com.zp.gmall.module.system.service.impl;

import com.zp.gmall.framework.common.pojo.PageResult;
import com.zp.gmall.module.system.controller.admin.dto.RolePageDTO;
import com.zp.gmall.module.system.entity.permission.RoleDO;
import com.zp.gmall.module.system.service.IRoleService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Author : zhengpanone
 * Date : 2025/3/28 14:36
 * Version : v1.0.0
 * Description:
 */
@SpringBootTest
class RoleServiceImplTest {

    @Autowired
    private IRoleService roleService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getRolePage() {
        PageResult<RoleDO> rolePage = roleService.getRolePage(new RolePageDTO());
        rolePage.getList().forEach(System.out::println);
    }
}