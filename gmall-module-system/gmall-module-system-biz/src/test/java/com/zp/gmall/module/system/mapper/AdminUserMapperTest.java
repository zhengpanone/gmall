//package com.zp.gmall.module.system.mapper;
//
//import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceAutoConfiguration;
//import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
//import com.zp.gmall.framework.common.util.PerfTracker;
//import com.zp.gmall.framework.mybatis.config.GmallMybatisAutoConfiguration;
//import com.zp.gmall.module.system.entity.user.AdminUserDO;
//import com.zp.gmall.module.system.mapper.user.AdminUserMapper;
//import jakarta.annotation.Resource;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.junit.jupiter.api.Test;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.sql.DataSource;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//
///**
// * Author : zhengpanone
// * Date : 2025/3/28 14:12
// * Version : v1.0.0
// * Description:
// */
//@SpringBootTest(
//        classes = {
//                AdminUserMapper.class,
//                DynamicDataSourceAutoConfiguration.class,
//                GmallMybatisAutoConfiguration.class,
//                MybatisPlusAutoConfiguration.class,
//                DataSourceAutoConfiguration.class,
//                DataSource.class,
//                SqlSessionFactory.class
//        }
//)
//@MapperScan(basePackages = "com.zp.gmall.module.system.mapper")
//@Slf4j
//class AdminUserMapperTest {
//
//
//    @Resource
//    private AdminUserMapper adminUserMapper;
//
//    @Test
//    void testCreateUser() {
//        try (PerfTracker.TimerContext ignore = PerfTracker.start()) {
//            AdminUserDO adminUserDO = new AdminUserDO();
//            adminUserDO.setUsername("admin1");
//            adminUserDO.setPassword("123456");
//            adminUserDO.setNickname("admin1");
//            adminUserMapper.insert(adminUserDO);
//        }
//    }
//
//    @Test
//    public void testGetUserById() {
//        AdminUserDO adminUserDO = adminUserMapper.selectById("1907357124108972034");
//        assertNotNull(adminUserDO);
//        assertEquals("admin", adminUserDO.getUsername());
//
//
//    }
//}