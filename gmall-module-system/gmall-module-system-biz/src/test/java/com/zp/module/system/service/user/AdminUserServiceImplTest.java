package com.zp.module.system.service.user;

import com.zp.framework.common.pojo.Result;
import com.zp.framework.test.core.ut.BaseDbUnitTest;
import com.zp.module.infra.api.config.ConfigApi;
import com.zp.module.system.service.user.impl.AdminUserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;


import static com.zp.framework.common.pojo.Result.ok;
import static com.zp.module.system.service.user.impl.AdminUserServiceImpl.USER_INIT_PASSWORD_KEY;
import static org.mockito.Mockito.when;

/**
 * Author : zhengpanone
 * Date : 2024/8/6 15:24
 * Version : v1.0.0
 * Description: TODO
 */
@Import(AdminUserServiceImpl.class)
public class AdminUserServiceImplTest extends BaseDbUnitTest {

    @MockBean
    private ConfigApi configApi;

    public void before() throws Exception {
        // mock 初始化密码
        when(configApi.getConfigValueByKey(USER_INIT_PASSWORD_KEY)).thenReturn(ok("yudaoyuanma"));
    }


    @Test
    void testCreateUser() {
    }
}
