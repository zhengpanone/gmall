package com.zp.module.system.service.user;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.zp.framework.common.enums.CommonStatusEnum;
import com.zp.framework.test.core.ut.BaseDbUnitTest;
import com.zp.module.infra.api.config.ConfigApi;
import com.zp.module.system.controller.admin.user.dto.UserSaveDTO;
import com.zp.module.system.dal.dataobject.user.AdminUserDO;
import com.zp.module.system.enums.common.SexEnum;
import com.zp.module.system.service.user.impl.AdminUserServiceImpl;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.zp.framework.common.pojo.Result.ok;
import static com.zp.framework.common.util.collection.SetUtils.asSet;
import static com.zp.framework.test.core.utils.AssertUtils.assertPojoEquals;
import static com.zp.framework.test.core.utils.RandomUtils.generatePhoneNumber;
import static com.zp.framework.test.core.utils.RandomUtils.randomPojo;
import static com.zp.module.system.service.user.impl.AdminUserServiceImpl.USER_INIT_PASSWORD_KEY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

/**
 * Author : zhengpanone
 * Date : 2024/8/6 15:24
 * Version : v1.0.0
 * Description:
 */
@Import(AdminUserServiceImpl.class)
public class AdminUserServiceImplTest extends BaseDbUnitTest {

    @Resource
    private AdminUserServiceImpl userService;
    @MockBean
    private ConfigApi configApi;
    @MockBean
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void before() throws Exception {
        // mock 初始化密码
        when(configApi.getConfigValueByKey(USER_INIT_PASSWORD_KEY)).thenReturn(ok("admin"));
    }


    @Test
    void testCreateUser() {
        UserSaveDTO userSaveDTO = randomPojo(UserSaveDTO.class, o -> {
            o.setSex(RandomUtil.randomEle(SexEnum.values()).getSex());
            o.setMobile(generatePhoneNumber());
            o.setPostIds(asSet("1", "2"));
            o.setId(IdUtil.fastSimpleUUID());
        });

        // mock passwordEncoder 的方法
        when(passwordEncoder.encode(eq(userSaveDTO.getPassword()))).thenReturn("admin");
        // 保存用户
        String userId = userService.createUser(userSaveDTO);
        AdminUserDO user = userService.getById(userId);
        assertPojoEquals(userSaveDTO, user, "password", "id");
        assertEquals("admin", user.getPassword());
        assertEquals(CommonStatusEnum.ENABLE.getStatus(), user.getStatus());
        // 断言关联岗位
        //List<UserPostDO> userPosts = userPostMapper.selectListByUserId(user.getId());
        //assertEquals(1L, userPosts.get(0).getPostId());
        //assertEquals(2L, userPosts.get(1).getPostId());

    }

}
