package com.zp.framework.test.core.ut;

import com.alibaba.druid.spring.boot3.autoconfigure.DruidDataSourceAutoConfigure;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.github.yulichang.autoconfigure.MybatisPlusJoinAutoConfiguration;
import com.zp.framework.datasource.config.GmallDataSourceAutoConfiguration;
import com.zp.framework.mybatis.config.GmallMybatisAutoConfiguration;
import com.zp.framework.test.config.SqlInitializationTestConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

/**
 * Author : zhengpanone
 * Date : 2024/8/6 15:26
 * Version : v1.0.0
 * Description: TODO
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = BaseDbUnitTest.Application.class)
@ActiveProfiles("test") // 使用application-test配置文件
@Sql(scripts = "/sql/clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD) // 每个单元测试结束后，清理 DB
public class BaseDbUnitTest {
    @Import({
            // DB配置
            // 自己的 DB 配置类
            GmallDataSourceAutoConfiguration.class,
            // Spring DB 自动配置类
            DataSourceAutoConfiguration.class,
            // Spring 事务自动配置类
            DataSourceTransactionManagerAutoConfiguration.class,
            // Druid 自动配置类
            DruidDataSourceAutoConfigure.class,
            // SQL 初始化
            SqlInitializationTestConfiguration.class,
            // MyBatis 配置类
            GmallMybatisAutoConfiguration.class, // 自己的 MyBatis 配置类
            MybatisPlusAutoConfiguration.class, // MyBatis 的自动配置类
            MybatisPlusJoinAutoConfiguration.class, // MyBatis 的Join配置类
    })
    public static class Application {

    }
}
