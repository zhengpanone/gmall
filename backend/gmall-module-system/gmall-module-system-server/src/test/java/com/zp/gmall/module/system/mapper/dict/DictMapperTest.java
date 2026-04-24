package com.zp.gmall.module.system.mapper.dict;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import com.zp.gmall.framework.mybatis.config.MybatisPlusConfiguration;
import com.zp.gmall.module.system.entity.dict.DictDO;
import com.zp.gmall.module.system.mapper.dict.DictMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

/**
 * DictMapperTest
 *
 * @author zhengpanone
 * @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)的作用是使用自定义的数据源，而非使用自动配置的嵌入式内存数据源
 * 如果你在项目正在使用类似于druid的连接池，在test模块的时候需要在application配置文件里面直接使用jdbc数据源即可，因为@MybatisPlusTest注解不会启动连接池框架
 * @since 2022-11-29
 */
@MybatisPlusTest // 只启动mybatis-plus组件不启动全环境
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 创建一个基于内存的数据库环境
@Rollback(value = true)    // 自动回滚，不写入数据库
@ActiveProfiles("test")  // 使用 test 配置文件
@Import(MybatisPlusConfiguration.class)
@Slf4j
public class DictMapperTest {
    @Autowired
    DictMapper dictMapper;

    @Test
    public void testInsert() {
        DictDO role = DictDO.builder().id("1").dictType("管理员").build();
        int insert = dictMapper.insert(role);
        log.info("是否插入成功={}", insert == 1);

    }
}