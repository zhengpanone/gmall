package com.zp.gmall.module.system.mapper;


import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zp.gmall.framework.common.util.PerfTracker;
import com.zp.gmall.module.system.entity.user.AdminUserDO;
import com.zp.gmall.module.system.mapper.user.AdminUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

/**
 * Author : zhengpanone
 * Date : 2025/3/27 19:57
 * Version : v1.0.0
 * Description:
 */

@Slf4j
class AdminUserMapperXMLTest {

    private static AdminUserMapper adminUserMapper;
    private static SqlSession sqlSession;

    @BeforeAll
    public static void setUpMybatisDatabase() {
        try {
            // 加载 MyBatis 配置文件
            InputStream inputStream = AdminUserMapperXMLTest.class.getClassLoader().getResourceAsStream("mybatisTestConfiguration.xml");

            // 创建 SqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new MybatisSqlSessionFactoryBuilder().build(inputStream);

            Configuration configuration = sqlSessionFactory.getConfiguration();
            // 配置拦截器
            MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
            mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());

            configuration.addInterceptor(mybatisPlusInterceptor);

            // 获取 SqlSession
            sqlSession = sqlSessionFactory.openSession(true);
            // 获取 AdminUserMapper 实例
            adminUserMapper = sqlSession.getMapper(AdminUserMapper.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    void selectByUsername() {
        try (PerfTracker.TimerContext ignore = PerfTracker.start()) {
            Page<AdminUserDO> adminUserPage = new Page<>();
            Page<AdminUserDO> adminUserDOPage = adminUserMapper.selectPage(adminUserPage, null);
            log.info("adminUserDOPage:{}", adminUserDOPage);
        }
    }


    // 在测试后关闭 SqlSession 连接
    @AfterAll
    public static void tearDown() {
        if (sqlSession != null) {
            sqlSession.close();
        }
    }
}