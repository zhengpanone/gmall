package com.zp.gmall.module.system.mapper;

import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceAutoConfiguration;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.zp.gmall.framework.common.pojo.PageResult;
import com.zp.gmall.module.system.controller.admin.dto.RolePageDTO;
import com.zp.gmall.module.system.entity.permission.RoleDO;
import com.zp.gmall.module.system.mapper.permission.RoleMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

/**
 * Author : zhengpanone
 * Date : 2025/3/28 14:12
 * Version : v1.0.0
 * Description:
 */
@SpringBootTest(
        classes = {
                RoleMapper.class,
                DynamicDataSourceAutoConfiguration.class,
                MybatisPlusAutoConfiguration.class,
                DataSourceAutoConfiguration.class,
                DataSource.class,
                SqlSessionFactory.class
        }
)
@MapperScan(basePackages = "com.zp.gmall.module.system.mapper")
@Slf4j
class RoleMapperTest {


    @Resource
    private RoleMapper roleMapper;

    @Test
    void selectPage() {
        PageResult<RoleDO> roleDOPageResult = roleMapper.selectPage(new RolePageDTO());
        log.info("roleDOPageResult:{}", roleDOPageResult);
    }
}