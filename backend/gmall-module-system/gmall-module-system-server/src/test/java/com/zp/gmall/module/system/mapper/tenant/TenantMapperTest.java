package com.zp.gmall.module.system.mapper.tenant;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import com.zp.gmall.framework.mybatis.config.MybatisPlusConfiguration;
import com.zp.gmall.module.system.entity.tenant.TenantDO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@MybatisPlusTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = true)
@ActiveProfiles("test")
@Import(MybatisPlusConfiguration.class)
@Slf4j
class TenantMapperTest {

    @Resource
    TenantMapper tenantMapper;

    @Test
    void selectListByWebsite() {
        List<TenantDO> tenants = tenantMapper.selectListByWebsite("127.0.0.1:5777");
        log.info("租户列表={}", tenants);
        Assertions.assertThat(tenants).isNotEmpty();
    }
}