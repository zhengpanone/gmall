package com.zp.gmall.module.system.service.impl;

import com.zp.gmall.module.system.controller.admin.dto.UserSaveDTO;
import com.zp.gmall.module.system.service.user.IAdminUserService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Author : zhengpanone
 * Date : 2025/4/1 19:15
 * Version : v1.0.0
 * Description:
 */
//@ActiveProfiles("local")
@SpringBootTest
class AdminUserServiceImplTest {
    @Resource
    private IAdminUserService adminUserService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createUser() {
        UserSaveDTO saveDTO = new UserSaveDTO();
        saveDTO.setUsername("admin");
        saveDTO.setNickname("nickName");
        adminUserService.createUser(saveDTO);
    }

    @Test
    void updateUser() {
    }

    @Test
    void getUserListByIds() {
    }
}