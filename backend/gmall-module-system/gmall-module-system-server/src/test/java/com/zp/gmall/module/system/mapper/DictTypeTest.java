package com.zp.gmall.module.system.mapper;

/**
 * @author : zhengpanone
 * Date : 2026/4/15 21:06
 * Version : v1.0.0
 * Description:
 */

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import com.zp.gmall.module.system.entity.dict.DictTypeDO;
import com.zp.gmall.module.system.mapper.dict.DictTypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;

/**
 * IRoleMapperTest
 *
 * @author zhengpanone
 * @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)的作用是使用自定义的数据源，而非使用自动配置的嵌入式内存数据源
 * 如果你在项目正在使用类似于druid的连接池，在test模块的时候需要在application配置文件里面直接使用jdbc数据源即可，因为@MybatisPlusTest注解不会启动连接池框架，典型的配置文件application.yml如下：
 * spring:
 * datasource:
 * url: jdbc:mysql://xxx.xxx.1.110:3306/test?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8
 * username: root
 * password: 123456
 * @since 2022-11-29
 */
@MybatisPlusTest // 只启动mybatis-plus组件不启动全环境
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 创建一个基于内存的数据库环境
@Rollback(value = true)    // 自动回滚，不写入数据库
@Slf4j
public class DictTypeTest {
    @Autowired
    DictTypeMapper dictTypeMapper;

    @Test
    public void testInsert() {
        DictTypeDO role = DictTypeDO.builder().id("1").name("管理员").build();
        int insert = dictTypeMapper.insert(role);
        log.info("是否插入成功={}", insert == 1);

    }
}