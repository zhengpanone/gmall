package com.zp.framework.test.core.ut;

import com.zp.framework.redis.config.GmallRedisAutoConfiguration;
import com.zp.framework.test.config.RedisTestConfiguration;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

/**
 * Author : zhengpanone
 * Date : 2024/8/29 18:24
 * Version : v1.0.0
 * Description: 依赖内存 Redis 的单元测试
 * 相比 {@link BaseDbUnitTest} 来说，从内存 DB 改成了内存 Redis
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = BaseRedisUnitTest.Application.class)
@ActiveProfiles("unit-test") // 设置使用 application-unit-test 配置文件
public class BaseRedisUnitTest {

    @Import({
            // Redis 配置类
            RedisTestConfiguration.class, // Redis 测试配置类，用于启动 RedisServer
            RedisAutoConfiguration.class, // Spring Redis 自动配置类
            GmallRedisAutoConfiguration.class, // 自己的 Redis 配置类
            RedissonAutoConfiguration.class, // Redisson 自动配置类
    })
    public static class Application {
    }

}
